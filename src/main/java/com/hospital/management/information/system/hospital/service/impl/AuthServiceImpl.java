package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.JwtAuthResponse;
import com.hospital.management.information.system.hospital.dto.LoginDto;
import com.hospital.management.information.system.hospital.dto.RegisterDto;
import com.hospital.management.information.system.hospital.entity.Role;
import com.hospital.management.information.system.hospital.entity.User;
import com.hospital.management.information.system.hospital.exception.HospitalAPIException;
import com.hospital.management.information.system.hospital.repository.RoleRepository;
import com.hospital.management.information.system.hospital.repository.UserRepository;
import com.hospital.management.information.system.hospital.security.JwtTokenProvider;
import com.hospital.management.information.system.hospital.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    //auth imports
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;




    @Override
    public String register(RegisterDto registerDto) {
        //Check if username already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new HospitalAPIException(HttpStatus.BAD_REQUEST,"Username already exists");
        }

        //Check if email already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new HospitalAPIException(HttpStatus.BAD_REQUEST,"Email already exists");
        }
        User user  = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsernameOrEmail(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
                    loginDto.getUsernameOrEmail());

            String role = null;
            if(userOptional.isPresent()){
                User loggedInUser = userOptional.get();
                Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

                if(optionalRole.isPresent()){
                    Role userRole = optionalRole.get();
                    role = userRole.getName();
                }
            }
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setRole(role);
            jwtAuthResponse.setAccessToken(token);

            return jwtAuthResponse;


        }catch (Exception ex){
            throw new HospitalAPIException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password!"
            );
        }
    }
}

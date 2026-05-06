package com.hospital.management.information.system.hospital.controller;


import com.hospital.management.information.system.hospital.dto.JwtAuthResponse;
import com.hospital.management.information.system.hospital.dto.LoginDto;
import com.hospital.management.information.system.hospital.dto.RegisterDto;
import com.hospital.management.information.system.hospital.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private AuthService authService;

    //Register REST API
    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse>login(@RequestBody LoginDto loginDto){
        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
    }


}

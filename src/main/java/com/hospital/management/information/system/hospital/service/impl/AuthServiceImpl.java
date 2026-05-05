package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.JwtAuthResponse;
import com.hospital.management.information.system.hospital.dto.LoginDto;
import com.hospital.management.information.system.hospital.dto.RegisterDto;
import com.hospital.management.information.system.hospital.repository.RoleRepository;
import com.hospital.management.information.system.hospital.repository.UserRepository;
import com.hospital.management.information.system.hospital.service.AuthService;

public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;



    @Override
    public String register(RegisterDto registerDto) {
        return "";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        return null;
    }
}

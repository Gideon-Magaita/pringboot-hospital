package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.JwtAuthResponse;
import com.hospital.management.information.system.hospital.dto.LoginDto;
import com.hospital.management.information.system.hospital.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}

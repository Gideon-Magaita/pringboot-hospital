package com.hospital.management.information.system.hospital.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class HospitalAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}

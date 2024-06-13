package com.DoctorManagement.DoctorManagementSystemDemo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorDetailsEmptyException;
import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorNotFoundException;

@ControllerAdvice
public class DoctorExceptionHandler {
    
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<?> handleDoctorNotFoundException(DoctorNotFoundException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorDetailsEmptyException.class)
    public ResponseEntity<?> handleDoctorDetailsEmptyException(DoctorDetailsEmptyException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}

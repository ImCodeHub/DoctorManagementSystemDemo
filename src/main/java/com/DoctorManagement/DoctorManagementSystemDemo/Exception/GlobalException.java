package com.DoctorManagement.DoctorManagementSystemDemo.Exception;

public class GlobalException {
    public static class DoctorNotFoundException extends RuntimeException{
        public DoctorNotFoundException(String message){
            super(message);
        }
    }

    public static class DoctorDetailsEmptyException extends RuntimeException{
        public DoctorDetailsEmptyException(String message){
            super(message);
        }
    }

    // you can create many Global exception class to handle exception as much as you want.
}

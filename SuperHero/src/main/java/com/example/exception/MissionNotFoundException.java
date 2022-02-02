package com.example.exception;

public class MissionNotFoundException extends RuntimeException{

    public MissionNotFoundException(Long id){
        super("Mission with id not found : " + id);
    }
}

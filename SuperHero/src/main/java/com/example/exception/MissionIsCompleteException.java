package com.example.exception;

public class MissionIsCompleteException extends RuntimeException{
    public MissionIsCompleteException(Long id){
        super("Mission with id is already finished : " + id);
    }
}

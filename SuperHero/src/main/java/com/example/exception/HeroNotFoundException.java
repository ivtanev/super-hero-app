package com.example.exception;

public class HeroNotFoundException extends RuntimeException{

    public HeroNotFoundException(Long id){
        super("Hero with id not found : " + id);
    }
}

package com.project.user.service.exceptions;

public class ResourceAlreadyExistException extends RuntimeException{
    
    public ResourceAlreadyExistException(){
        super("Resource Already Exists");
    }
    public ResourceAlreadyExistException(String message){
        super(message);
    }
}

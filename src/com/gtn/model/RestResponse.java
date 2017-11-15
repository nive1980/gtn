package com.gtn.model;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class RestResponse <T> implements Model {

    private boolean success;
    private String message;
    private T data;

    public RestResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        System.out.println("message is "+message);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

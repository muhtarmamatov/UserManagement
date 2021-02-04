package com.muhtar.utils;

public class ResultInfo<T> {
    private String message;
    private T object;
    private Status status;
    public enum Status{
        SUCCESS,ERROR
    }

    public ResultInfo( T object, String message, Status status) {
        this.message = message;
        this.object = object;
        this.status = status;
    }

    public ResultInfo(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

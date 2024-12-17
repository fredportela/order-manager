package com.example.moutis.orderservice.exception;

public class ErrorResponse {
    private String message;
    private String codeError;

    public ErrorResponse(String message, String codeError) {
        this.message = message;
        this.codeError = codeError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }
}
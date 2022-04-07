package com.incs.spendtracking.exception;


import com.incs.spendtracking.response.generic.ResponseCode;

public class ApiException extends RuntimeException {
    private int code;
    private String message;

    public ApiException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

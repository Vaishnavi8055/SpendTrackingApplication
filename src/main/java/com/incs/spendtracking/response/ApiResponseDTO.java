
package com.incs.spendtracking.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.incs.spendtracking.response.generic.ResponseCode;
import com.incs.spendtracking.response.generic.ResponseDTO;

import java.util.Locale;
import java.util.StringJoiner;


@JsonPropertyOrder({"code", "data", "message"})
@SuppressWarnings({"rawtypes", "unused"})
public class ApiResponseDTO<T> implements ResponseDTO<T> {

    private int code;
    private String message = "";
    private T data;

    public ApiResponseDTO() {
    }

    public ApiResponseDTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponseDTO(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponseDTO(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public ResponseDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    private Locale getLocale(String locale) {
        return locale != null ? new Locale(locale) : Locale.UK;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("code = " + code)
                .add("message = " + message)
                .add("data = " + data).toString();
    }
}

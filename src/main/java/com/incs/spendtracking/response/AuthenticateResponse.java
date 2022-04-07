package com.incs.spendtracking.response;

public class AuthenticateResponse {

    private String jwtToken;

    public AuthenticateResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}

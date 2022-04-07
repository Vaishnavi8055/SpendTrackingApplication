package com.incs.spendtracking.response.generic;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * This class is used as standard response when validation error will occur.
 * <br><br>{@link #addErrorMessage(String)} it is used to give message in ApiResponseDTO.
 */
public class ValidationErrorResponse {
    private Set<String> errorMessages = new HashSet<>();

    public Set<String> getErrorMessages() {
        return errorMessages;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorMessages.add(errorMessage);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("errorMessages = " + errorMessages)
                .toString();
    }
}

/* ***********************************************************************
 * 83incs CONFIDENTIAL
 * ***********************************************************************
 *
 *  [2017] - [2022] 83incs Ltd.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of 83incs Ltd, IoT83 Ltd, its suppliers (if any), its subsidiaries (if any) and
 * Source Code Licensees (if any).  The intellectual and technical concepts contained
 * herein are proprietary to 83incs Ltd, IoT83 Ltd, its subsidiaries (if any) and
 * Source Code Licensees (if any) and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from 83incs Ltd or IoT83 Ltd.
 ****************************************************************************
 */

package com.incs.spendtracking.response.generic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 *This class is used to throw exception when signed in authority violates the parameter
 *  which was set to accept data.
 * <br><br>{@link #fieldErrors} is name of the field and error message.
 */
public class ValidationErrorDTO {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ValidationErrorDTO() {
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public ValidationErrorDTO setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }

    public void addError(FieldErrorDTO errorDTO) {
        this.fieldErrors.add(errorDTO);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("fieldErrors = " + fieldErrors)
                .toString();
    }

    public String toJSON() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException ignored) {
        }
        return "";
    }


    public static class FieldErrorDTO {
        private String field;
        private String message;

        public FieldErrorDTO() {
        }

        public FieldErrorDTO(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public FieldErrorDTO setField(String field) {
            this.field = field;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public FieldErrorDTO setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", "" + "{", "}")
                    .add("field = " + field)
                    .add("message = " + message)
                    .toString();
        }
    }
}

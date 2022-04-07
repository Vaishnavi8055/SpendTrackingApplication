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

package com.incs.spendtracking.response.utils;



import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.locale.MessageByLocale;
import com.incs.spendtracking.response.ApiResponseDTO;
import com.incs.spendtracking.response.generic.ResponseCode;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.generic.ValidationErrorDTO;
import com.incs.spendtracking.response.generic.ValidationErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public final class ResponseUtil {
    private static final Map data = new HashMap();
    @Autowired
    private MessageByLocale messageByLocale;

    public Locale getLocale(String locale) {
        return locale != null ? new Locale(locale) : Locale.UK;
    }

    /**
     * This method sets the values in message and the data.
     *
     * @return the anonymous object with parameter message and data.
     */
    public ResponseDTO ok() {
        String message = messageByLocale.getMessage(String.valueOf(ApiResponseCode.SUCCESS.getCode()));
        return new ApiResponseDTO(message, data);
    }

    /**
     * This method sets the values in message and the data.
     *
     * @param data it's a object which consist of some information.
     * @return the anonymous object with parameter message and data.
     */
    public ResponseDTO ok(Object data) {
        String message = messageByLocale.getMessage(String.valueOf(ApiResponseCode.SUCCESS.getCode()));
        return new ApiResponseDTO(message, data);
    }

    /**
     * This method sets the values in message and the data.
     *
     * @param responseCode it's a object which helps in setting up the value of message.
     * @return the anonymous object with parameter message and data.
     */
    public ResponseDTO ok(ResponseCode responseCode) {
        String message = messageByLocale.getMessage(String.valueOf(responseCode.getCode()));
        return new ApiResponseDTO(message, data);
    }

    /**
     * This method sets the values in message and the data.
     *
     * @param data         it's a object which consist of some information.
     * @param responseCode it's a object which helps in setting up the value of message.
     * @return the anonymous object with parameter message and data.
     */
    public ResponseDTO ok(Object data, ResponseCode responseCode) {
        String message = messageByLocale.getMessage(String.valueOf(responseCode.getCode()));
        return new ApiResponseDTO(message, data);
    }

    /**
     * This method sets the values in message and the data.
     *
     * @param data         it's a object which consist of some information.
     * @param responseCode it's a object which helps in setting up the value of message.
     * @param locale       it is used for setting up the country code.
     * @return the anonymous object with parameter message and data.
     */
    public ResponseDTO ok(Object data, ResponseCode responseCode, String locale) {
        String message = messageByLocale.getMessage(String.valueOf(responseCode.getCode()));
        return new ApiResponseDTO(message, data);
    }

    /**
     * This method sets the values in message and the data.
     *
     * @param responseCode it's a object which helps in setting up the value of message.
     * @param locale       it is used for setting up the country code.
     * @return the anonymous object with parameter message and data.
     */
    public ResponseDTO ok(ResponseCode responseCode, String locale) {
        String message = messageByLocale.getMessage(String.valueOf(responseCode.getCode()));
        return new ApiResponseDTO(message, data);
    }

    /**
     * This method sets the values in code, message and the data.
     *
     * @param code it's a error code which is of integer type.
     * @return the anonymous object with parameter code,message and data.
     */
    public ResponseDTO exception(int code) {
        String message = messageByLocale.getMessage(String.valueOf(code));
        return new ApiResponseDTO(code, message, data);
    }

    /**
     * This method sets the values in code, message and the data.
     *
     * @param code    it's a error code which is of integer type.
     * @param message it's a error message which is of String type.
     * @return the anonymous object with parameter code,message and data.
     */
    public ResponseDTO exception(int code, String message) {
        return new ApiResponseDTO(code, message, data);
    }

    /**
     * This method sets the values in code, message and the data.
     *
     * @param responseCode it's a object which helps in setting up the value of message and the code.
     * @return the anonymous object with parameter code,message and data.
     */
    public ResponseDTO exception(ResponseCode responseCode) {
        String message = messageByLocale.getMessage(String.valueOf(responseCode.getCode()));
        return new ApiResponseDTO(responseCode.getCode(), message, data);
    }

    /**
     * This method sets the values in code, message and the data.
     *
     * @param code    it's a error code which is of integer type.
     * @param message it's a error message which is of String type.
     * @return the anonymous object with parameter code,message and data.
     */
    public ResponseDTO validationFailed(int code, String message) {
        return new ApiResponseDTO(code, message, data);
    }

    /**
     * This method sets the values in code, message and the data.
     *
     * @param code               it's a error code which is of integer type.
     * @param validationErrorDTO it's a object which helps in setting up the error message.
     * @return the anonymous object with parameter code,message and data.
     */
    public ResponseDTO validationFailed(int code, ValidationErrorDTO validationErrorDTO) {
        return new ApiResponseDTO(code, "Missing / Invalid Parameter(s)", validationErrorDTO.getFieldErrors());
    }

    /**
     * This method sets the values in code, message and the data.
     *
     * @param code          it's a error code which is of integer type.
     * @param errorResponse it's a object which helps in setting up the error message.
     * @return the anonymous object with parameter code,message and data.
     */
    public ResponseDTO validationFailed(int code, ValidationErrorResponse errorResponse) {
        return new ApiResponseDTO(code, "Missing / Invalid Parameter(s)", errorResponse.getErrorMessages());
    }

}

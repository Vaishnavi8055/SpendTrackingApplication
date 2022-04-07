package com.incs.spendtracking.exception.handler;

import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.exception.ApiException;
import com.incs.spendtracking.exception.ValidationException;
import com.incs.spendtracking.locale.MessageByLocale;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.generic.ValidationErrorResponse;
import com.incs.spendtracking.response.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger LOG = LogManager.getLogger(ApiExceptionHandler.class);

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private MessageByLocale messageByLocale;

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = {ApiException.class})
    public ResponseDTO<?> handleGenericException(ApiException e) {
        LOG.error(String.format("API Exception: Got [[%s]] exception with message: %s", e.getClass().getName(), e.getMessage()));
        return responseUtil.exception(ApiResponseCode.ERROR.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public ResponseDTO<?> handleGenericException(ValidationException e) {
        LOG.error(String.format("Validation Exception: Got [[%s]] exception with message: %s", e.getClass().getName(), e.getMessage()));
        return responseUtil.exception(e.getCode(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseDTO exception(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) throws Exception {
        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        LOG.error("Invalid / Missing Parameters " + errorResponse);
        LOG.error(String.format("########### Got [[%s]] exception with message: %s at path =>  [[%s]]  ########  ########", e.getClass().getName(), e.getMessage(), e.getStackTrace()[0].toString()));
        e.getBindingResult().getFieldErrors().forEach(field -> errorResponse.addErrorMessage(messageByLocale.getMessage(field.getDefaultMessage())));
        return responseUtil.validationFailed(HttpStatus.BAD_REQUEST.value(), String.valueOf(errorResponse));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseDTO<?> exception(NoHandlerFoundException e) {
        LOG.error(String.format("API Exception: Got [[%s]] exception with message: %s", e.getClass().getName(), e.getMessage()));
        return responseUtil.exception(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseDTO exception(HttpRequestMethodNotSupportedException e) {
        LOG.error(String.format("API Exception: Got [[%s]] exception with message: %s", e.getClass().getName(), e.getMessage()));
        return responseUtil.exception(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseDTO<?> exception(Exception e) {
        LOG.error(String.format("API Exception: Got [[%s]] exception with message: %s", e.getClass().getName(), e.getMessage()));
        return responseUtil.exception(ApiResponseCode.ERROR.getCode());
    }
}

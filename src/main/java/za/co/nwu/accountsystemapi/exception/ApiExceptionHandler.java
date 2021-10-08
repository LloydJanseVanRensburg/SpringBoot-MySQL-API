package za.co.nwu.accountsystemapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e)
    {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        // 1. Create payload containing exception details
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2. Return ResponseEntity
        return new ResponseEntity<>(apiException, badRequest);
    }
}

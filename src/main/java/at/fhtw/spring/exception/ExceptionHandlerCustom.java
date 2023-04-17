package at.fhtw.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerCustom {
    @ExceptionHandler(value =  {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        //create payload containing details
        ApiException apiException = ApiException
                .builder()
                .msg(e.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        //return response entity
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        //create payload containing details
        ValidationException validationException = ValidationException
                .builder()
                .msg(errors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        //return response entity

        return new ResponseEntity<>(validationException, HttpStatus.BAD_REQUEST);
    }
}

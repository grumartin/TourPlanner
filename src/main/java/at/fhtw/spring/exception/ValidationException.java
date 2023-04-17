package at.fhtw.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ValidationException {
    private final List<String> msg;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}


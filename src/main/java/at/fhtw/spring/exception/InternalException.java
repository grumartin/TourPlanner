package at.fhtw.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class InternalException {
    private final String msg;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}

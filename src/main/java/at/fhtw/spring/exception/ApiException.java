package at.fhtw.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiException {
    private final String msg;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}

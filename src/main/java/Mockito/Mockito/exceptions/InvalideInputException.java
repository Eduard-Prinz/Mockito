package Mockito.Mockito.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalideInputException extends RuntimeException{
    public InvalideInputException() {
    }

    public InvalideInputException(String message) {
        super(message);
    }

    public InvalideInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalideInputException(Throwable cause) {
        super(cause);
    }

    public InvalideInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
package Mockito.Mockito.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeExistsException extends RuntimeException{
    public EmployeeExistsException() {
    }

    public EmployeeExistsException(String message) {
        super(message);
    }

    public EmployeeExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeExistsException(Throwable cause) {
        super(cause);
    }

    public EmployeeExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
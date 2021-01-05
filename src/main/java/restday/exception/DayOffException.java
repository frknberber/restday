package restday.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found")
public class DayOffException extends RuntimeException {

    public DayOffException(String message){
        super(message);
    }

    public DayOffException(String message, Throwable cause) {
        super(message, cause);
    }
}

package solanteq.test.task.data.processing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidFormatException extends RuntimeException{

    public InvalidFormatException(Throwable cause) {
        super(cause);
    }
}
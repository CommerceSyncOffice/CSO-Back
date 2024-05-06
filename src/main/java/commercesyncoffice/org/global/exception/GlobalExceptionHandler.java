package commercesyncoffice.org.global.exception;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getExceptionCode());

        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidException(MethodArgumentNotValidException e) {

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String defaultMessage = "";
        for (ObjectError allError : allErrors) {
            defaultMessage = allError.getDefaultMessage();
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse((HttpStatus) e.getStatusCode(), defaultMessage);

        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }
}

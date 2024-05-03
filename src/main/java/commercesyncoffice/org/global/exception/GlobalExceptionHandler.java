package commercesyncoffice.org.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getExceptionCode());

        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }
}

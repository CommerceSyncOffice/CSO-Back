package commercesyncoffice.org.global.exception;

import commercesyncoffice.org.domain.admin.exception.AdminException;
import commercesyncoffice.org.global.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(AdminException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidException(MethodArgumentNotValidException e) {

        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ExceptionResponse exceptionResponse = ExceptionResponse.of(defaultMessage);

        return ResponseEntity.status(e.getStatusCode()).body(exceptionResponse);
    }
}

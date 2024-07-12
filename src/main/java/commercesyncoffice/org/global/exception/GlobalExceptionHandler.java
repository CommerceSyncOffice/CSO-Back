package commercesyncoffice.org.global.exception;

import commercesyncoffice.org.domain.admin.exception.AdminException;
import commercesyncoffice.org.domain.brand.exception.BrandException;
import commercesyncoffice.org.domain.category.exception.CategoryException;
import commercesyncoffice.org.domain.item.exception.ItemException;
import commercesyncoffice.org.domain.itemserial.exception.ItemSerialException;
import commercesyncoffice.org.domain.member.exception.MemberException;
import commercesyncoffice.org.domain.membergroup.exception.MemberGroupException;
import commercesyncoffice.org.domain.membergroupmember.exception.MemberGroupMemberException;
import commercesyncoffice.org.domain.membergrouprole.exception.MemberGroupRoleException;
import commercesyncoffice.org.domain.memberrole.exception.MemberRoleException;
import commercesyncoffice.org.domain.stockreceive.exception.StockReceiveException;
import commercesyncoffice.org.domain.stockrequest.exception.StockRequestException;
import commercesyncoffice.org.domain.store.exception.StoreException;
import commercesyncoffice.org.domain.storeitem.exception.StoreItemException;
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

    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(BrandException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CategoryException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(ItemException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(ItemSerialException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(ItemSerialException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(MemberException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MemberGroupException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(MemberGroupException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MemberGroupMemberException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(MemberGroupMemberException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MemberGroupRoleException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(MemberGroupRoleException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MemberRoleException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(MemberRoleException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(StockReceiveException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(StockReceiveException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(StockRequestException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(StockRequestException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(StoreException e) {

        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(StoreItemException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(StoreItemException e) {

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

package commercesyncoffice.org.global.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record SuccessResponse<T> (
        @Schema(description = "성공 여부")
        boolean success,

        @Schema(description = "응답 메세지")
        @NonNull
        String message,

        @Schema(description = "데이터")
        @JsonInclude(value = NON_NULL)
        T data
) implements CommonResponse {

    public static <T> SuccessResponse<T> success(String message, T data) {
        return SuccessResponse.<T>builder()
                              .success(true)
                              .message(message)
                              .data(data)
                              .build();
    }

    public static SuccessResponse<?> success(String message) {
        return SuccessResponse.builder().success(true).message(message).build();
    }
}

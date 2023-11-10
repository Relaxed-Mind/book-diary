package capstone.bookdiary.exception.response;

import capstone.bookdiary.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@Builder
public class ApiErrorResponse {
    private int code;
    private String message;

    public static ResponseEntity<ApiErrorResponse> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                        ApiErrorResponse.builder()
                                .code(e.getHttpStatus().value())
                                .message(e.getMessage())
                                .build()
                );
    }
}

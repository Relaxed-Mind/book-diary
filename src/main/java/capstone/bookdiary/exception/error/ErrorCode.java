package capstone.bookdiary.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보가 존재하지 않습니다."),
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "해당 이메일은 이미 존재합니다."),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 데이터가 존재하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."),
    API_REQUEST_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "외부 API 호출에 문제가 있습니다"),
    IMAGE_ALREADY_EXIST(HttpStatus.CONFLICT, "해당 스크랩은 이미 이미지가 생성 됐습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

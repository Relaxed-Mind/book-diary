package capstone.bookdiary.exception;

import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.exception.exceptions.UserNotFoundException;
import capstone.bookdiary.exception.response.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    ResponseEntity<ApiErrorResponse> dataNotFoundExceptionHandle(DataNotFoundException e){
        return ApiErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ApiErrorResponse> userNotFoundExceptionHandle(UserNotFoundException e){
        return ApiErrorResponse.toResponseEntity(e.getErrorCode());
    }
}

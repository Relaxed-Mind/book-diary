package capstone.bookdiary.exception;

import capstone.bookdiary.exception.error.ErrorCode;
import capstone.bookdiary.exception.exceptions.DataNotFoundException;
import capstone.bookdiary.exception.exceptions.EmailDuplicateException;
import capstone.bookdiary.exception.exceptions.ImageAlreadyExistException;
import capstone.bookdiary.exception.exceptions.UserNotFoundException;
import capstone.bookdiary.exception.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

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
    @ExceptionHandler(RestClientException.class)
    ResponseEntity<ApiErrorResponse> apiRequestExceptionHandle(RestClientException e){
        return ApiErrorResponse.toResponseEntity(ErrorCode.API_REQUEST_ERROR);
    }

    @ExceptionHandler(EmailDuplicateException.class)
    ResponseEntity<ApiErrorResponse> emailDuplicationExceptionHandle(EmailDuplicateException e){
        return ApiErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageAlreadyExistException.class)
    ResponseEntity<ApiErrorResponse> imageAlreadyExistException(ImageAlreadyExistException e){
        return ApiErrorResponse.toResponseEntity(e.getErrorCode());
    }
}

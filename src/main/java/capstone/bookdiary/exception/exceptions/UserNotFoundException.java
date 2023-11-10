package capstone.bookdiary.exception.exceptions;

import capstone.bookdiary.exception.error.ErrorCode;

public class UserNotFoundException extends CustomException{

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}

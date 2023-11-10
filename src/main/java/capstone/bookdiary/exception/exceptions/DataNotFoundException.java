package capstone.bookdiary.exception.exceptions;

import capstone.bookdiary.exception.error.ErrorCode;

public class DataNotFoundException extends CustomException{
    public DataNotFoundException() {
        super(ErrorCode.DATA_NOT_FOUND);
    }
    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}

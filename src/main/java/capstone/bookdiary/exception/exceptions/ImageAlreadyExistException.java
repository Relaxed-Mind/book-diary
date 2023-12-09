package capstone.bookdiary.exception.exceptions;

import capstone.bookdiary.exception.error.ErrorCode;

public class ImageAlreadyExistException extends CustomException{
    public ImageAlreadyExistException() {
        super(ErrorCode.IMAGE_ALREADY_EXIST);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}

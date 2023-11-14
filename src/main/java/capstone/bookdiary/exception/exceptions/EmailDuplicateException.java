package capstone.bookdiary.exception.exceptions;

import capstone.bookdiary.exception.error.ErrorCode;

public class EmailDuplicateException extends CustomException{
    public EmailDuplicateException() {
        super(ErrorCode.EMAIL_DUPLICATE);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}

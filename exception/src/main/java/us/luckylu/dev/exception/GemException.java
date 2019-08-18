package us.luckylu.dev.exception;

import us.luckylu.dev.common.exception.BaseException;
import us.luckylu.dev.common.exception.BaseExceptionEnum;

/**
 * @author lu
 * @create 2019-03-25 17:39
 */
public class GemException extends BaseException {

    public GemException(String message, Integer code) {
        super(message, code);
    }

    public GemException(String message, Throwable cause, Integer code) {
        super(message, cause, code);
    }

    public GemException(Throwable cause, Integer code) {
        super(cause, code);
    }

    public GemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace, code);
    }

    public GemException(BaseExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public GemException(Integer code) {
        super(code);
    }

    public GemException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode());
    }


}

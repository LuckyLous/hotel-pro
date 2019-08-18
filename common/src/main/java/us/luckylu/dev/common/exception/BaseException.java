package us.luckylu.dev.common.exception;

public class BaseException extends RuntimeException {

    protected Integer code;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public BaseException(BaseExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public BaseException(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

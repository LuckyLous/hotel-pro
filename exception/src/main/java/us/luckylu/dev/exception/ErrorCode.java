package us.luckylu.dev.exception;

public enum ErrorCode {
    //
    GEM_CREATE_INDEX_ERROR(10001, "宝石创建索引失败"),
    GEM_CREATE_DOCUMENT_ERROR(10002, "宝石创建文档失败"),

    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

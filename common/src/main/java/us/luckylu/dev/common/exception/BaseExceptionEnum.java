package us.luckylu.dev.common.exception;

public enum BaseExceptionEnum {

    //
    SUCCESS("成功", 200),

    SYSTEM_BUSY("系统繁忙，请稍候再试", 500),

    URL_NOR_FOUND( "无效的url", 404),
    INVALID_PARAMEGER("参数不符合要求", 501),
    REFLECTION_GET_FAILED("GlobalExceptionEnum.reflectionGetFailed", 502),
    UNSUPPORTED_ENCODING("GlobalExceptionEnum.unsupportedEncodingException", 503),

    INVALID_ACCOUNT_PASSWORD("账号或密码不正确", 504),
    ACCESS_DENIED("无访问权限", 505),
    ANNOYMOUS("您处于登出状态，请重新登录", 506),
    ACCOUNT_LOCKED("账号已被锁定", 507),
    ACCOUNT_DISABLED("账号已被禁用", 508),
    HTTP_METHOD_NOT_SUPPORT("错误的http请求方法", 509),
    ;

    private String message;
    private Integer code;

    private BaseExceptionEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

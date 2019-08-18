package us.luckylu.dev.model.constants;

import lombok.Getter;

/**
 * @author lu
 * @create 2019-03-22 15:49
 */
@Getter
public enum UserStatusEnum {
    //
    LEAVED(0, "离开"),
    ORDERING(1, "预定"),
    HOUSING(2, "在住"),
    ;

    private Integer code;
    private String msg;

    UserStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

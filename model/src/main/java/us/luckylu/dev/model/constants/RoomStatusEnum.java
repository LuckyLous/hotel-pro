package us.luckylu.dev.model.constants;

import lombok.Getter;

/**
 * @author lu
 * @create 2019-03-22 15:49
 */
@Getter
public enum RoomStatusEnum {
    //
    AVAILABLE(0, "空闲中"),
    ORDERING(1, "预定中"),
    USING(2, "使用中"),
    ;

    private Integer code;
    private String msg;

    RoomStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

package us.luckylu.dev.model.constants;

import lombok.Getter;

/**
 * @author lu
 * @create 2019-03-22 15:55
 */
@Getter
public enum OpenRoomTypeEnum {
    //
    NOW_ORDER(0, "预定中"),
    NOW_HOUSING(1, "空闲中"),
    ;

    private Integer code;
    private String msg;

    OpenRoomTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

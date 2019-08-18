package us.luckylu.dev.model.constants;

import lombok.Getter;

/**
 * @author lu
 * @create 2019-03-22 15:55
 */
@Getter
public enum OpenRoomStatusEnum {
    //
    ORDER_RECORD(0, "预定记录"),
    HOUSING_RECORD(1, "在住记录"),
    HISTORY_RECORD(2, "历史记录"),
    ;

    private Integer code;
    private String msg;

    OpenRoomStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

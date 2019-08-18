package us.luckylu.dev.model.constants;

import lombok.Getter;

/**
 * @author lu
 * @create 2019-03-22 15:49
 */
@Getter
public enum RoomOverdueStatusEnum {
    //
    NOT_OVERDUE(0, "不超期"),
    OVERDUE(1, "超期"),
    ;

    private Integer code;
    private String msg;

    RoomOverdueStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

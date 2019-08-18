package us.luckylu.dev.client.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.luckylu.dev.model.OpenRoom;
import us.luckylu.dev.model.User;
import us.luckylu.dev.model.constants.OpenRoomTypeEnum;
import us.luckylu.dev.model.constants.RoomStatusEnum;
import us.luckylu.dev.model.constants.UserStatusEnum;

import java.util.Objects;

/**
 * @author lu
 * @create 2019-03-22 17:44
 */
@Service
public class OpenRoomServiceImpl {

    @Transactional(rollbackFor = Exception.class)
    public void create(OpenRoom openRoom){
        String sql = "UPDATE room SET room_status = ? WHERE room_id=?";

        if(Objects.equals(OpenRoomTypeEnum.NOW_ORDER.getCode(), openRoom.getType())){
            openRoom.setStatus(RoomStatusEnum.ORDERING.getCode());
        }else {
            openRoom.setStatus(RoomStatusEnum.USING.getCode());
        }

        if(Objects.equals(OpenRoomTypeEnum.NOW_ORDER.getCode(), openRoom.getType())){
            openRoom.setStatus(UserStatusEnum.ORDERING.getCode());
        }else {
            openRoom.setStatus(UserStatusEnum.HOUSING.getCode());
        }

        User user = new User();
        openRoom.setDeposit(user.getAdvancePay());
        sql = "UPDATE client SET cli_status = ?,advance_pay=? WHERE cli_id = ?";
    }
}

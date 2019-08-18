package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;

public class Room {
    @ApiModelProperty(value="客房id")
    private Integer id;

    @ApiModelProperty(value="客房类型id")
    private Integer roomTypeId;

    @ApiModelProperty(value="客房名")
    private String roomName;

    @ApiModelProperty(value="客房状态,0为空闲中，1为预定中，2为使用中")
    private Integer roomStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }
}
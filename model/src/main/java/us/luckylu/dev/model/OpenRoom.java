package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

public class OpenRoom {
    @ApiModelProperty(value="开房id")
    private Integer id;

    @ApiModelProperty(value="开房类型，1为现住，0为预定")
    private Integer type;

    @ApiModelProperty(value="开房状态,2为历史记录,1为在住记录,0为预定记录")
    private Integer status;

    @ApiModelProperty(value="开房日期")
    private LocalDateTime createAt;

    @ApiModelProperty(value="客户id")
    private Integer userId;

    @ApiModelProperty(value="客房id")
    private Integer roomId;

    @ApiModelProperty(value="预期到达时间")
    private LocalDateTime expeArrivetime;

    @ApiModelProperty(value="预期离开时间")
    private LocalDateTime expeLeavetime;

    @ApiModelProperty(value="押金,如果超期,应该按规定扣除")
    private Double deposit;

    @ApiModelProperty(value="续期天数,续期后预计离开时间要更新")
    private Integer extendDays;

    @ApiModelProperty(value="超期状态,1表示超期,0不超期")
    private Integer overdueStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getExpeArrivetime() {
        return expeArrivetime;
    }

    public void setExpeArrivetime(LocalDateTime expeArrivetime) {
        this.expeArrivetime = expeArrivetime;
    }

    public LocalDateTime getExpeLeavetime() {
        return expeLeavetime;
    }

    public void setExpeLeavetime(LocalDateTime expeLeavetime) {
        this.expeLeavetime = expeLeavetime;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Integer getExtendDays() {
        return extendDays;
    }

    public void setExtendDays(Integer extendDays) {
        this.extendDays = extendDays;
    }

    public Integer getOverdueStatus() {
        return overdueStatus;
    }

    public void setOverdueStatus(Integer overdueStatus) {
        this.overdueStatus = overdueStatus;
    }
}
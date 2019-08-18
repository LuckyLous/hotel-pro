package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutRoom {
    @ApiModelProperty(value="退房id")
    private Integer id;

    @ApiModelProperty(value="开房id")
    private Integer openId;

    @ApiModelProperty(value="退房日期")
    private LocalDateTime createAt;

    @ApiModelProperty(value="住房金额")
    private BigDecimal roomAmount;

    @ApiModelProperty(value="超期金额")
    private BigDecimal fineAmount;

    @ApiModelProperty(value="支付方式")
    private String payMethod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public BigDecimal getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(BigDecimal roomAmount) {
        this.roomAmount = roomAmount;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }
}
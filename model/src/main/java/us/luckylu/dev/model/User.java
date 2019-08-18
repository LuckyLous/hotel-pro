package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;

public class User {
    @ApiModelProperty(value="客户id")
    private Integer id;

    @ApiModelProperty(value="客户名")
    private String realName;

    @ApiModelProperty(value="客户性别")
    private String sex;

    @ApiModelProperty(value="客户手机号")
    private String phone;

    @ApiModelProperty(value="证件类型id")
    private Integer cardTypeId;

    @ApiModelProperty(value="证件号")
    private String cardNumber;

    @ApiModelProperty(value="客户状态,0表示离开,1表示预定,2表示在住")
    private Integer status;

    @ApiModelProperty(value="预付金额，客户离开后,预付款改为0")
    private Double advancePay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAdvancePay() {
        return advancePay;
    }

    public void setAdvancePay(Double advancePay) {
        this.advancePay = advancePay;
    }
}
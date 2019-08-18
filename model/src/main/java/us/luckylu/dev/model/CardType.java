package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

public class CardType {
    @ApiModelProperty(value="证件类型id")
    private Integer id;

    @ApiModelProperty(value="证件类型")
    private String cardRoleName;

    @ApiModelProperty(value="罚款价格，按小时计算")
    private BigDecimal finePrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardRoleName() {
        return cardRoleName;
    }

    public void setCardRoleName(String cardRoleName) {
        this.cardRoleName = cardRoleName == null ? null : cardRoleName.trim();
    }

    public BigDecimal getFinePrice() {
        return finePrice;
    }

    public void setFinePrice(BigDecimal finePrice) {
        this.finePrice = finePrice;
    }
}
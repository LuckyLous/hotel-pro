package us.luckylu.dev.model;

import io.swagger.annotations.ApiModelProperty;

public class RoomType {
    @ApiModelProperty(value="客房类型id")
    private Integer id;

    @ApiModelProperty(value="客房类型")
    private String name;

    @ApiModelProperty(value="客房价格，按小时计算")
    private Double price;

    @ApiModelProperty(value="客房描述")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
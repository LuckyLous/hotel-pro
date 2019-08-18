package us.luckylu.dev.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class BaseReq {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value="id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

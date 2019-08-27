package us.luckylu.dev.common.model.dto.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto<T> {

    @ApiModelProperty(value = "状态")
    private Integer code = 200;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "数据")
    private List<T> data;

    public ListResponseDto(List<T> data) {
        this.data = data;
    }
}


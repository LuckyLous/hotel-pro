package us.luckylu.dev.common.model.dto.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    @ApiModelProperty(value = "状态")
    private Integer code = 200;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;

    public ResponseDto(T data) {
        this.data = data;
    }
}

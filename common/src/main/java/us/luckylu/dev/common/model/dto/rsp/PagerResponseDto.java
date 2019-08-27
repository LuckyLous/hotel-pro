package us.luckylu.dev.common.model.dto.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.luckylu.dev.common.model.Pager;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagerResponseDto<T> {

    @ApiModelProperty(value = "状态")
    private Integer code = 200;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "数据")
    private Pager<T> data = (Pager<T>) OBJECT_PAGE;

    private static final Pager<Object> OBJECT_PAGE = new Pager<Object>();

    public PagerResponseDto(Pager<T> data) {
        this.data = data;
    }
}


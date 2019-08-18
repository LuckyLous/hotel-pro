package us.luckylu.dev.common.model.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.validation.constraints.Min;

@ToString
public class PagerDto {

    public final static int DEFAULT_PAGE_NO = 1;
    public final static int DEFAULT_PER_PAGE = 10;

    @ApiModelProperty(value="页码")
    @Min(value = 1, message = "页码必须为正整数")
    private Integer page;

    @ApiModelProperty(value="每页条数")
    @Min(value = 1, message = "每页条数必须为正整数")
    private Integer count;

    @ApiModelProperty(hidden = true)
    private Integer total;

    public PagerDto() {}

    public PagerDto(Integer page, Integer count) {
        this.page = page;
        this.count = count;
    }

    public Integer getPage() {
        return this.page == null || this.page <= 0 ? DEFAULT_PAGE_NO : this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return this.count == null || this.count <= 0 ? DEFAULT_PER_PAGE : this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return (getPage() - 1) * getCount();
    }

    public Integer calTotalPage() {
        return this.total % getCount() == 0 ? total / getCount() : total / getCount() + 1;
    }
}

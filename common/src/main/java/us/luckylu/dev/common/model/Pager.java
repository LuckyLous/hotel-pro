package us.luckylu.dev.common.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pager<T> {

    public final static int DEFAULT_PAGE_NO = 1;
    public final static int DEFAULT_PER_PAGE = 10;

    /**
     * 当前页码
     */
    private Integer page;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 每页条数
     */
    private Integer count;

    /**
     * 返回的数据List集合
     */
    private List<T> array = Collections.emptyList();

    /**
     * 返回的数据Map集合
     */
    private Map<String, Object> extras;

    public Pager() {
    }

    public Pager(Integer page, Integer count) {
        this.page = page;
        this.count = count;
        this.extras = new HashMap<>();
    }

    public Integer getOffset() {
        return (getPage() - 1) * getCount();
    }

    public Integer getTotalPageNo() {
        return total % getCount() == 0 ? total / getCount() : total / getCount() + 1;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getArray() {
        return array;
    }

    public void setArray(List<T> array) {
        this.array = array;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    public Integer getPage() {
        return page == null || page <= 0L ? DEFAULT_PAGE_NO : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count == null || count <= 0L ? DEFAULT_PER_PAGE : count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

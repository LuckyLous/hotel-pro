package us.luckylu.dev.common.base;

import us.luckylu.dev.common.model.Pager;
import us.luckylu.dev.common.model.dto.req.PagerDto;

import java.util.List;

/**
 * @author lu
 * @create 2019-08-18 23:22
 */
public interface BaseMapper <T extends BaseModel> {

    int insert(T record);

    int insertSelective(T record);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

    int deleteByPrimaryKey(Integer id);

    T selectByPrimaryKey(Integer id);

    List<T> list();

    Pager<T> paginate(PagerDto pagerDto);

}

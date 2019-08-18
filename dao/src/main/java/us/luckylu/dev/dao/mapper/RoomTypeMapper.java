package us.luckylu.dev.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import us.luckylu.dev.model.RoomType;
import us.luckylu.dev.model.RoomTypeExample;

public interface RoomTypeMapper {
    long countByExample(RoomTypeExample example);

    int deleteByExample(RoomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    List<RoomType> selectByExample(RoomTypeExample example);

    RoomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoomType record, @Param("example") RoomTypeExample example);

    int updateByExample(@Param("record") RoomType record, @Param("example") RoomTypeExample example);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);
}
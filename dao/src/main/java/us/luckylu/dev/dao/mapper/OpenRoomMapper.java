package us.luckylu.dev.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import us.luckylu.dev.model.OpenRoom;
import us.luckylu.dev.model.OpenRoomExample;

public interface OpenRoomMapper {
    long countByExample(OpenRoomExample example);

    int deleteByExample(OpenRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpenRoom record);

    int insertSelective(OpenRoom record);

    List<OpenRoom> selectByExample(OpenRoomExample example);

    OpenRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpenRoom record, @Param("example") OpenRoomExample example);

    int updateByExample(@Param("record") OpenRoom record, @Param("example") OpenRoomExample example);

    int updateByPrimaryKeySelective(OpenRoom record);

    int updateByPrimaryKey(OpenRoom record);
}
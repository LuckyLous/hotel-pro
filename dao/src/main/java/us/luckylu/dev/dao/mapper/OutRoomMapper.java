package us.luckylu.dev.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import us.luckylu.dev.model.OutRoom;
import us.luckylu.dev.model.OutRoomExample;

public interface OutRoomMapper {
    long countByExample(OutRoomExample example);

    int deleteByExample(OutRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OutRoom record);

    int insertSelective(OutRoom record);

    List<OutRoom> selectByExample(OutRoomExample example);

    OutRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OutRoom record, @Param("example") OutRoomExample example);

    int updateByExample(@Param("record") OutRoom record, @Param("example") OutRoomExample example);

    int updateByPrimaryKeySelective(OutRoom record);

    int updateByPrimaryKey(OutRoom record);
}
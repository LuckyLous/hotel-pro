package us.luckylu.dev.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import us.luckylu.dev.model.CardType;
import us.luckylu.dev.model.CardTypeExample;

public interface CardTypeMapper {
    long countByExample(CardTypeExample example);

    int deleteByExample(CardTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardType record);

    int insertSelective(CardType record);

    List<CardType> selectByExample(CardTypeExample example);

    CardType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardType record, @Param("example") CardTypeExample example);

    int updateByExample(@Param("record") CardType record, @Param("example") CardTypeExample example);

    int updateByPrimaryKeySelective(CardType record);

    int updateByPrimaryKey(CardType record);
}
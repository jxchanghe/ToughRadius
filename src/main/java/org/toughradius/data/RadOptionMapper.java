package org.toughradius.data;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.toughradius.model.RadOption;
import org.toughradius.model.RadOptionExample;

public interface RadOptionMapper {
    int countByExample(RadOptionExample example);

    int deleteByExample(RadOptionExample example);

    int deleteByPrimaryKey(String optionName);

    int insert(RadOption record);

    int insertSelective(RadOption record);

    List<RadOption> selectByExampleWithRowbounds(RadOptionExample example, RowBounds rowBounds);

    List<RadOption> selectByExample(RadOptionExample example);

    RadOption selectByPrimaryKey(String optionName);

    int updateByExampleSelective(@Param("record") RadOption record, @Param("example") RadOptionExample example);

    int updateByExample(@Param("record") RadOption record, @Param("example") RadOptionExample example);

    int updateByPrimaryKeySelective(RadOption record);

    int updateByPrimaryKey(RadOption record);
}
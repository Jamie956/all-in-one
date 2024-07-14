package org.example;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select({"${sql}"})
    @ResultType(ArrayList.class)
    List<User> customParamSql(@Param("sql") String sql);
}
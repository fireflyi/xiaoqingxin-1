package com.lfy.dao.slave.mapper;

import com.lfy.domain.Tab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lishihao4 on 2019/1/16.
 * DESC TODO
 */
@Mapper
public interface TabMapper {

    @Select("select * from tab")
    List<Tab> list();
}

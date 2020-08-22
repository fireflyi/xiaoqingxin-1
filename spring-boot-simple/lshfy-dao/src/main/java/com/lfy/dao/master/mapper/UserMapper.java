package com.lfy.dao.master.mapper;

import com.lfy.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lishihao4 on 2019/1/16.
 * DESC TODO
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> list();

}

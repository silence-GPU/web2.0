package com.jiejiezhuzhu.dao;
import com.jiejiezhuzhu.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserDao {

        @Insert("insert into tbl_user (username,password) values(#{username},#{password})")
        public int save(User user);

        @Update("update tbl_user set password = #{password} where id = #{id}")
        public int update(User user);

        @Delete("delete from tbl_user where id = #{id}")
        public int delete(Integer id);

        @Select("select * from tbl_user where id = #{id}")
        public User getById(Integer id);

        @Select("select * from tbl_user where username = #{username}")
        public User getByName(User user);

        @Select("select * from tbl_user where username = #{username} and password = #{password}")
        public User getByNP(User user);

        @Select("select * from tbl_user")
        public List<User> getAll();
    }

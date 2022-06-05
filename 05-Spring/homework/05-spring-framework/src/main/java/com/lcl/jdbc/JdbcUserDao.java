package com.lcl.jdbc;

public interface JdbcUserDao {

    UserDo findUserById(int id) throws Exception;

}

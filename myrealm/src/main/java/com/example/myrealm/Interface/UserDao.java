package com.example.myrealm.Interface;

import com.example.myrealm.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hmh on 2016/11/7.
 * 操作数据库接口的Dao
 *
 */
public interface UserDao {

    /**插入一个用户
     * @param user 需要插入的用户对象
     * */
    void insert(User user)throws SQLException;

    /**
     * 获得所有的用户
     *
     * */
    List<User> getAllUser()throws SQLException;

    /**
     * 更新一个用户
     * @param user 需要更新的用户类
     * @return 跟新后的对象
     * */
    User updateUser(User user)throws SQLException;

    /***
     * 根据姓名修改新姓名
     * @param name1 老名字
     * @param name2 新名字
     * @throws SQLException
     * */
    void updateUser(String name1, String name2)throws SQLException;

    /***
     * 根据id删除用户
     * @param id 用户主键id
     * */
    void deleteUser(int id)throws SQLException;


    /**
     * 异步添加用户
     * @param user 需要添加的用户
     * @throws SQLException
     * */
    void insertUserAsync(User user)throws SQLException;

    /***
     * 按名字或者年龄查找第一个User
     * @param name1 用户名字
     * @param age1 用户年龄
     * */
    User findByNameOrAge(String name1, int age1)throws SQLException;

    /***
     * 清除所有用户
     * */
    void deleteAll()throws SQLException;

    /**关闭事务*/
    void closeRealm();


}

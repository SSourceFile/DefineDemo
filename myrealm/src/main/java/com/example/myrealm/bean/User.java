package com.example.myrealm.bean;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by hmh on 2016/11/7.
 * 实体类、属性类似于字段
 */
public class User extends RealmObject{

    @PrimaryKey
    private int id;         //主键
    @Required
    private String userName;    //用户名，不能为空
    private int userAge;        //年龄
    private String userAdress;      //地址
    private String userWork;        //工作
    private String userSex;         //性别
    @Ignore             //忽略
    private boolean isHasFriend;    //是否有男女朋友

    public User(){}
    public User(int id, String userName, int userAge, String userAdress, String userWork, String userSex, boolean isHasFriend) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
        this.userAdress = userAdress;
        this.userWork = userWork;
        this.userSex = userSex;
        this.isHasFriend = isHasFriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    public String getUserWork() {
        return userWork;
    }

    public void setUserWork(String userWork) {
        this.userWork = userWork;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public boolean isHasFriend() {
        return isHasFriend;
    }

    public void setHasFriend(boolean hasFriend) {
        isHasFriend = hasFriend;
    }
}

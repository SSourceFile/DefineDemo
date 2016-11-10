package com.example.myrealm.dao;

import android.content.Context;

import com.example.myrealm.Interface.UserDao;
import com.example.myrealm.bean.User;
import com.example.myrealm.utils.RealmUtils;

import java.sql.SQLException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by hmh on 2016/11/7.
 */
public class UserDaoImpl implements UserDao {

    private Context context;
    private Realm mRealms;

    public UserDaoImpl(Context context){
        //取得数据库对象
        mRealms = RealmUtils.getInstance(context).getRealm();
    }

    /**同步插入用户*/
    @Override
    public void insert(User user) throws SQLException {
        mRealms.beginTransaction(); //必须先开启事务
//        User user1 = mRealms.copyFromRealm(user);  //吧User对象复制到realm
        User user1 = mRealms.copyToRealm(user);
        mRealms.commitTransaction();    //提交事务
//        mRealms.close();        //必须关闭，否则容易造成内存泄漏
    }

    /**返回所有的User对象，并按照名字的首字母排序*/
    @Override
    public List<User> getAllUser() throws SQLException {
        List<User> list = null;
        RealmResults<User> results = mRealms.where(User.class).findAll();
//        results.sort("userName", Sort.DESCENDING); //针对字符串的排序，但目前并不是支持所有字符集
        list = results;
//        mRealms.close();    //关闭
        return results;
    }

    /***更新一个User*/
    @Override
    public User updateUser(User user) throws SQLException {
        mRealms.beginTransaction();     //开启事务
        User user1 = mRealms.copyToRealmOrUpdate(user);   //用户信息更新到realm
        mRealms.commitTransaction();        //提交事务
//        mRealms.close();        //关闭事务
        return user1;
    }

    /**根据姓名修改姓名*/
    @Override
    public void updateUser(String name1, String name2) throws SQLException {
        //1、开启事务
        mRealms.beginTransaction();
        //2、执行,查询出name和name的User对象对比
        mRealms.where(User.class).equalTo("userName", name1)
                .findFirst()
                .setUserName(name2);    //吧新名字和查处的名字替换
        //3、提交事务
        mRealms.commitTransaction();
        //4、关闭事务
//        mRealms.close();
    }


    /**根据用户的id删除一个用户*/
    @Override
    public void deleteUser(int id) throws SQLException {
        User id1 = mRealms.where(User.class).equalTo("id", id).findFirst();
        //开启事务
        mRealms.beginTransaction();
        id1.deleteFromRealm();      //从数据库中删除
        //提交事务
        mRealms.commitTransaction();
//        mRealms.close();
    }

    /**异步插入User
     * 注意：  一个Realm只能在同一个线程访问，在子线程中进行数据库操作必须重新
     * 获取realm对象。
     *
     * */
    @Override
    public void insertUserAsync(final User user) throws SQLException {
        mRealms.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //开启事务
                mRealms.beginTransaction();
                User user1 = mRealms.copyToRealm(user);
                //提交事务
                mRealms.commitTransaction();
                mRealms.close();    //关闭事务
            }
        });
        //外部也需要关闭事务。
//        mRealms.close();
    }

    /**返回第一个指定名字或者年龄的对象*/
    @Override
    public User findByNameOrAge(String name1, int age1) throws SQLException {
        User user = mRealms.where(User.class)
                .equalTo("userName", name1)   //相当于where name = name1
                .or()   //或。连接查询条件，没有这个方法时会默认是&连接
                .equalTo("userAge", age1)   //相当于where age = age1;
                .findFirst();
        //整体相当于select * from(表明) where name = name1 or age = age1 limit 1;
        //关闭数据库
//        mRealms.close();
        return user;
    }

    /***删除所有*/
    @Override
    public void deleteAll() throws SQLException {
        //开启事务
        mRealms.beginTransaction();
        mRealms.where(User.class).findAll().deleteAllFromRealm();
        //提交事务iu
        mRealms.commitTransaction();
//        mRealms.close();
    }

    @Override
    public void closeRealm() {
        mRealms.close();
    }
}

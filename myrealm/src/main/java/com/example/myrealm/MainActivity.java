package com.example.myrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myrealm.Interface.UserDao;
import com.example.myrealm.bean.User;
import com.example.myrealm.dao.UserDaoImpl;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;
    private TextView mMsg;
    private boolean s = false;
    private static final int TYPE_SHOW = 1;     //显示加减
    private static final int TYPE_HIDE = 2;     //隐藏加减

    private int currentStatic  = TYPE_HIDE;     //默认隐藏
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMsg = (TextView) findViewById(R.id.addAll);

        mMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s){
                    //显示加减
                    s = false;
                }else{
                    //隐藏加减
                    s = true;
                }
            }
        });
        //获取对象
        userDao = new UserDaoImpl(this);

        try {
            //删除所有
            userDao.deleteAll();
            User user = new User();
            user.setId(10);
            user.setUserName("王尼玛");
            user.setUserAge(22);
            user.setUserAdress("广州");
            user.setUserSex("男");
            user.setUserWork("嫖妓");
            user.setHasFriend(true);
            userDao.insert(user);


            for(int i = 0; i < 5; i++){
                userDao.insert(new User(i, "屌丝", 11, "水利", "工程", "男", false));
            }
            Log.e("123", userDao.getAllUser().toString());
            mMsg.setText(""+userDao.getAllUser().toString());

            Log.e("123", userDao.findByNameOrAge("王尼玛", 66)+"查询一");
            Log.e("123", "查询二："+userDao.findByNameOrAge("狗子", 11));
            userDao.updateUser("屌丝", "二狗子");
            Log.e("123", userDao.getAllUser()+"//");
            userDao.deleteUser(1);
            Log.e("123", "\n"+"删除后----"+userDao.getAllUser().toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

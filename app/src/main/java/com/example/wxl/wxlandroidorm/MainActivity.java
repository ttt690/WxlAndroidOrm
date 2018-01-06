package com.example.wxl.wxlandroidorm;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.personal_library.rxjava_permission.Permission;
import com.example.personal_library.rxjava_permission.WxlRxUtils;
import com.example.wxl.wxlandroidorm.Model.book;
import com.example.wxl.wxlandroidorm.Model.jinera;
import com.example.wxl.wxlandroidorm.dao.bookDao;
import com.example.wxl.wxlandroidorm.dao.jineraDao;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity implements View.OnClickListener {

    private long insert;

    private bookDao bookdao;
    private jineraDao jineradao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);

        bookdao = Myapplication.daoSession.getBookDao();
        jineradao = Myapplication.daoSession.getJineraDao();

    }
    //插入一条
    private void insert() {

        insert = jineradao.insert(new jinera(null,"老酒鬼"));

        Log.e("**********", "insert=" + insert);

    }

    /**
     * 插入用户集合
     *
     * @param
     */
    public void insertjineraList() {
        List<jinera> users=new ArrayList<>();
        users.add(new jinera(null,"牛头1"));
        users.add(new jinera(null,"牛头2"));
        if (users == null || users.isEmpty()) {
            return;
        }

        jineradao.insertInTx(users);
    }

    private  void selectall(){

        List<jinera> users = jineradao.queryBuilder()
                .where(jineraDao.Properties.Title.eq("牛头1") )
                .orderAsc(jineraDao.Properties.Id)//按字段排序
                .list();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getTitle() + ",";
        }

        Log.e("*********","查询全部数据==>" + userName);


    }
    private void updata(){

        WxlRxUtils.getpermission(this,200, Permission.STORAGE,Permission.CAMERA )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("*******permission", "onNext=" + s);

                    }
                });
    }
    /**
     * 删除一条记录
     *
     * @param
     */
    public void deleteUser(jinera jinera) {

        jineradao.delete(jinera);

    }
    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(jinera user) {

        jineradao.update(user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
//                insert();
                insertjineraList();
                break;
            case R.id.btn2:
                selectall();
                break;
            case R.id.button2:
                updata();
                break;
            case R.id.button3:

                break;
        }
    }
}

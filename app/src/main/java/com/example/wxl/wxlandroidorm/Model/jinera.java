package com.example.wxl.wxlandroidorm.Model;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wxl on 2017/12/22.
 */
@Entity
public class jinera {


    @Id(autoincrement = true)
    private Long id;

    @NonNull
    private String title;

    @Generated(hash = 785113389)
    public jinera(Long id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

    @Generated(hash = 915266120)
    public jinera() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

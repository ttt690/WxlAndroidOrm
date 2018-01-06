package com.example.wxl.wxlandroidorm.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wxl on 2017/12/22.
 */
@Entity
public class book {

    @Id(autoincrement = true)
    private Long icon;

    @NotNull
    private String booname;

    @NotNull
    private String forpeople;

    @Generated(hash = 143710764)
    public book(Long icon, @NotNull String booname, @NotNull String forpeople) {
        this.icon = icon;
        this.booname = booname;
        this.forpeople = forpeople;
    }

    @Generated(hash = 1626308135)
    public book() {
    }

    public Long getIcon() {
        return this.icon;
    }

    public void setIcon(Long icon) {
        this.icon = icon;
    }

    public String getBooname() {
        return this.booname;
    }

    public void setBooname(String booname) {
        this.booname = booname;
    }

    public String getForpeople() {
        return this.forpeople;
    }

    public void setForpeople(String forpeople) {
        this.forpeople = forpeople;
    }
 

}

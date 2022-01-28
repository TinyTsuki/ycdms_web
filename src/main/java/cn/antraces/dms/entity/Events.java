package cn.antraces.dms.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Events)实体类
 *
 * @author silver
 * @since 2021-11-16 11:39:56
 */
public class Events implements Serializable {

    private int id;

    private String name;
    /**
     * 活动日期
     */
    private Date date;

    public Events() {
        id = 0;
        name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}


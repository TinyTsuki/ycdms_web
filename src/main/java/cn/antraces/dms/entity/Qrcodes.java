package cn.antraces.dms.entity;

import java.io.Serializable;

/**
 * (Qrcodes)实体类
 *
 * @author silver
 * @since 2021-11-16 11:39:56
 */
public class Qrcodes implements Serializable {

    private int id;

    private String token;

    private int uid;

    private long creatime;

    private long usetime;

    private int worker;

    public Qrcodes() {
        id = 0;
        token = "";
        uid = 0;
        creatime = 0;
        usetime = 0;
        worker = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getCreatime() {
        return creatime;
    }

    public void setCreatime(long creatime) {
        this.creatime = creatime;
    }

    public long getUsetime() {
        return usetime;
    }

    public void setUsetime(long usetime) {
        this.usetime = usetime;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

}


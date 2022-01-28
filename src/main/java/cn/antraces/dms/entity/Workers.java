package cn.antraces.dms.entity;

import java.io.Serializable;

/**
 * (Wokers)实体类
 *
 * @author silver
 * @since 2021-11-16 11:39:56
 */
public class Workers implements Serializable {

    private int id;
    private String name;
    private String job;
    private String token;
    private long qq;
    private String psw;

    public Workers() {
        id = 0;
        name = "";
        job = "";
        token = "";
        qq = 0;
        psw = "";
    }

    @Override
    public String toString() {
        return "Workers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", token='" + token + '\'' +
                ", qq=" + qq +
                ", psw='" + psw + '\'' +
                '}';
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getQq() {
        return qq;
    }

    public void setQq(long qq) {
        this.qq = qq;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}


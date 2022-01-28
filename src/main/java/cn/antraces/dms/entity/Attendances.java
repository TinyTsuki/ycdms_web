package cn.antraces.dms.entity;

import java.io.Serializable;

/**
 * (Attendances)实体类
 *
 * @author silver
 * @since 2021-11-16 11:39:56
 */
public class Attendances implements Serializable {

    private int id;
    /**
     * 活动编号
     */
    private int eid;
    /**
     * 会员编号
     */
    private int uid;
    /**
     * 1请假 2出勤
     */
    private int status;

    public Attendances() {
        id = 0;
        eid = 0;
        uid = 0;
        status = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}


package cn.antraces.dms.entity;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"code", "msg", "date", "data"})
public class BackJson<T> {
    private int code;
    private String msg;
    private long date;
    private T data;

    public BackJson() {
        code = 0;
        msg = "";
        date = System.currentTimeMillis();
        data = null;
    }

    @Override
    public String toString() {
        return "BackJson{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

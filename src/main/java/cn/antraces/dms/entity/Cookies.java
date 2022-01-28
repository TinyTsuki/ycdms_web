package cn.antraces.dms.entity;

public class Cookies {
    private int id;
    private String key;
    private String value;
    private long time;

    @Override
    public String toString() {
        return "Cookies{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", time=" + time +
                '}';
    }

    public Cookies() {
        id = 0;
        key = "";
        value = "";
        time = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

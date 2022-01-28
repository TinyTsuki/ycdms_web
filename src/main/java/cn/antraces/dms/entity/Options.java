package cn.antraces.dms.entity;

public class Options {
    private String key;
    private String value;

    @Override
    public String toString() {
        return "Options{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Options() {
        key = "";
        value = "";
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
}

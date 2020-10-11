package cf.wellod.bean;

public class GoodsInfo {
    Integer sid;
    Integer cid;
    String EN13;
    String name;
    String info;

    public  Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getEN13() {
        return EN13;
    }

    public void setEN13(String EN13) {
        this.EN13 = EN13;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "sid=" + sid +
                ", cid=" + cid +
                ", EN13='" + EN13 + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}

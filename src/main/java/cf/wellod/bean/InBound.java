package cf.wellod.bean;

import java.util.Date;

public class InBound {
    Integer id;
    String gid; //商品ID
    Integer wid; //仓库ID
    Integer eid; // EmployeeID
    Date op_time; // 操作日期
    Integer quantity; //操作数量
    Integer threshold; // 阈值

    @Override
    public String toString() {
        return "InBound{" +
                "id=" + id +
                ", gid='" + gid + '\'' +
                ", wid=" + wid +
                ", eid=" + eid +
                ", op_time=" + op_time +
                ", quantity=" + quantity +
                ", threshold=" + threshold +
                '}';
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Date getOp_time() {
        return op_time;
    }

    public void setOp_time(Date op_time) {
        this.op_time = op_time;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package cf.wellod.bean;

import java.util.Date;

public class OutBound {
    Integer id;
    String gid; //商品ID
    Integer wid; //仓库ID
    Integer eid; // EmployeeID
    Date opTime; // 操作日期
    Integer quantity; //操作数量
    String reason; // 出库原因

    @Override
    public String toString() {
        return "OutBound{" +
                "id=" + id +
                ", gid='" + gid + '\'' +
                ", wid=" + wid +
                ", eid=" + eid +
                ", opTime=" + opTime +
                ", quantity=" + quantity +
                ", reason='" + reason + '\'' +
                '}';
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

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

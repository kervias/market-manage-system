package cf.wellod.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OutBound {
    private Integer id;
    private String gid; //商品ID
    private Integer wid; //仓库ID
    private Integer eid; // EmployeeID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opTime; // 操作日期
    private Integer quantity; //操作数量
    private int reason; // 出库原因

    @Override
    public String toString() {
        return "OutBound{" +
                "id=" + id +
                ", gid='" + gid + '\'' +
                ", wid=" + wid +
                ", eid=" + eid +
                ", opTime=" + opTime +
                ", quantity=" + quantity +
                ", reason=" + reason +
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

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }
}

package cf.wellod.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class OrderList {
    String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date payTime;
    Double amount;
    Integer status; // 订单状态，0: 未支付 1: 已支付 2：已取消
    List<OrderDetail> detailList;

    @Override
    public String toString() {
        return "OrderList{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", amount=" + amount +
                ", status=" + status +
                ", detailList=" + detailList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OrderDetail> detailList) {
        this.detailList = detailList;
    }
}

package cf.wellod.bean;

public class OrderDetail {
    private String gid;
    private String oid;
    private Integer quantity;
    private Double discount;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "gid='" + gid + '\'' +
                ", oid='" + oid + '\'' +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}

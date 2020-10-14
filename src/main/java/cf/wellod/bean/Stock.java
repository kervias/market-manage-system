package cf.wellod.bean;

import com.opencsv.bean.CsvBindByName;

public class Stock {

    @CsvBindByName(column = "gid",required = true)
    private String gid; //商品ID
    @CsvBindByName(column = "wid",required = true)
    private Integer wid; //仓库ID
    @CsvBindByName(column = "quantity",required = true)
    private Integer quantity; // 数量
    private Integer threshold; // 临界值

    @Override
    public String toString() {
        return "Stock{" +
                "gid='" + gid + '\'' +
                ", wid=" + wid +
                ", quantity=" + quantity +
                ", threshold=" + threshold +
                '}';
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
}

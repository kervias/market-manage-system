package cf.wellod.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class Goods {
    private String id;
    private String EN13;
    private String batch; //生产批次
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date prodDate;
    private Integer expDate; // 注意！！！！ 此为保质期
    private String unit;
    private Double buyPrice; //售价
    private Double price; //进价
    private Double discount; //折扣 （0,1]
    private Integer shelfQuantity;

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", EN13='" + EN13 + '\'' +
                ", batch='" + batch + '\'' +
                ", prodDate=" + prodDate +
                ", expDate=" + expDate +
                ", unit='" + unit + '\'' +
                ", buyPrice=" + buyPrice +
                ", price=" + price +
                ", discount=" + discount +
                ", shelfQuantity=" + shelfQuantity +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEN13() {
        return EN13;
    }

    public void setEN13(String EN13) {
        this.EN13 = EN13;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = StringUtils.trim(batch);
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Integer getExpDate() {
        return expDate;
    }

    public void setExpDate(Integer expDate) {
        this.expDate = expDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getShelfQuantity() {
        return shelfQuantity;
    }

    public void setShelfQuantity(Integer shelfQuantity) {
        this.shelfQuantity = shelfQuantity;
    }
}

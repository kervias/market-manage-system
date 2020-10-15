package cf.wellod.bean;

public class DaySale {
    private String id;
    private Double amount; //销售额
    private Double cost; //成本

    @Override
    public String toString() {
        return "DaySale{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", cost=" + cost +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}

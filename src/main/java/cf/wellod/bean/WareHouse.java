package cf.wellod.bean;

import org.apache.commons.lang3.StringUtils;

public class WareHouse {
    private Integer id;
    private String name;
    private String info;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.trimToNull(name);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = StringUtils.trimToNull(info);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = StringUtils.trimToNull(address);
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

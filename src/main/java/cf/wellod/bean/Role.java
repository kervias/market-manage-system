package cf.wellod.bean;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class Role {

    private Integer id;
    private String name;
    private String info;

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
        this.name = StringUtils.trimToEmpty(name);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = StringUtils.trimToEmpty(info);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}

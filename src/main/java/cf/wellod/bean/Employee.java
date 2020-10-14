package cf.wellod.bean;

//import cf.wellod.mapper.RoleMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Employee {
    private Integer id;
    private String name;
    private boolean gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String tel;
    private String username;
    private String email;
    private String password;
    private boolean forbidden;
    private Integer rid;
    private String salt;
    //private String rolename;

//    public String getRolename() {
//        return rolename;
//    }
//
//    public void setRolename(String rolename) {
//        this.rolename = rolename;
//    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", forbidden=" + forbidden +
                ", rid=" + rid +
                ", salt='" + salt + '\'' +
                //", rolename='" + rolename + '\'' +
                '}';
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

    public boolean isGender() {
        return gender;
    }

    public boolean getGender(){
        /*if(this.gender == false){
            return "男";
        }else {
            return "女";
        }*/
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirth(){
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = StringUtils.trimToEmpty(tel);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = StringUtils.trimToEmpty(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = StringUtils.trimToEmpty(password);
    }

    public boolean isForbidden() {
        return forbidden;
    }

    public boolean getForbidden(){
        /*if(this.forbidden == false){
            return "否";
        }else {
            return "是";
        }*/
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }


    public Integer getRid(){
        return rid;
    }

    /*public String getRid2() {

        System.out.println("hhhhhhhaaa");
        Role role = roleMapper.selectRole(this.rid);
        System.out.println(role.getName());
        return role.getName();
    }*/

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = StringUtils.trimToEmpty(salt);
    }

}

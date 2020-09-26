package cf.wellod.mapper;

import cf.wellod.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
    // 插入一个Employee
    public void insertEmployee(Employee employee);

    // 查询Employee(不包括密码和salt)
    public Employee selectEmployee(Integer id);

    // 更新Employee信息(不包括创建时间、ID、密码、salt), 用户自己修改个人资料
    public void updateEmployeeBaseInfo(Employee employee);

    // 更新Employee全部信息（不包括创建时间、ID,包括密码、salt）, 管理员修改资料
    public void updateEmployeeAllInfo(Employee employee);

    // 更新个人密码
    public void updateEmloyeePwd(String pwd, String salt, Integer id);

    // 删除Employee
    public void deleteEmployee(Integer id);


}

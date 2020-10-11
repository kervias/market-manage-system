package cf.wellod.mapper;

import cf.wellod.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    // 添加Role
    public void insertRole(Role role);

    // 更新角色信息
    public void updateRole(Role role);

    // 查询角色信息
    public Role selectRole(Integer id);

    // 获取表中的数据项个数
    public  Integer getRolesCount();

    // 查询某个范围内的数据
    public List<Role> getRolesByRange(Integer start, Integer num);

    //查询表中的所有数据
    public List<Role> getRoles();

    // 批量删除某些Roles
    public void deleteRoles(List<Integer> delIdList);

    // 删除某个Role
    public void deleteRoleById(Integer id);

}

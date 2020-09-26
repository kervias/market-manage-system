package cf.wellod.mapper;

import cf.wellod.bean.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    // 添加Role
    public void insertRole(Role role);

    // 更新角色信息
    public void updateRole(Role role);

    // 查询角色信息
    public Role selectRole(Integer id);

    // 删除角色
    public void deleteRole(Integer id);

}

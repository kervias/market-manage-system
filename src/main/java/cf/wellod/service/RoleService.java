package cf.wellod.service;

import cf.wellod.bean.Role;
import cf.wellod.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Transactional
    public HashMap<String,Object> getRolesTwo(){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            retJson.put("code", 0);
            retJson.put("msg", "success");
            //retJson.put("count", count);
            List<Role> list = roleMapper.getRoles();
            retJson.put("data",list);
            System.out.println(list);
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("data", new ArrayList<Role>());
        }
        return retJson;
    }

    @Transactional
    public HashMap<String,Object> getRoles(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = roleMapper.getRolesCount();
        if(count >= 0 && limit > 0 && page > 0)
        {
            int pageNum = (int)Math.ceil(count.floatValue()/limit);
            if(pageNum == 0) pageNum++;
            if(page > pageNum) page = pageNum;
            int start = (page-1)*limit+1;
            System.out.println("start "+start);
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("count", count);
            List<Role> list = roleMapper.getRolesByRange(start,limit);
            retJson.put("data", list);
            System.out.println(list);
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<Role>());
        }

        return retJson;
    }

    @Transactional
    public Object deleteRoles(List<Integer> list){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(list.size() != 0)
            {
                roleMapper.deleteRoles(list);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "操作数量不能为0");
            }
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    @Transactional
    public HashMap<String,Object> deleteRoleById(Integer id){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            roleMapper.deleteRoleById(id);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    @Transactional
    public HashMap<String,Object> insertRole(Role role){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        if(role.getName() != null && role.getInfo() != null && role.getId() == null)
        {
            retJson.put("code", 0);
            retJson.put("msg", "操作成功");
            try{
                roleMapper.insertRole(role);
            }catch (Exception e){
                System.out.println(e);
                retJson.put("code", -1);
                retJson.put("msg", "操作失败");
            }
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "操作失败");
        }
        return retJson;
    }

    @Transactional
    public Object updateRole(Role role){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            roleMapper.updateRole(role);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }
}

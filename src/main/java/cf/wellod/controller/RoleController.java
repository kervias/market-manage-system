package cf.wellod.controller;

import cf.wellod.bean.Role;
import cf.wellod.mapper.RoleMapper;
import cf.wellod.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@ResponseBody
public class RoleController {
    @Autowired
    RoleMapper roleMapper;

    @PostMapping("/add")
    public Object addRole(@RequestBody Role role){
        HashMap<String, Object> retJson = new HashMap<String, Object>();
        //role.setCreateTime(DateUtil.getCurrDateTime());
        if(role.getName().length() == 0 || role.getInfo().length() == 0){
            retJson.put("code",-1);
            retJson.put("msg", "字段不能为空");
        }else{
            try{
                roleMapper.insertRole(role);
                retJson.put("code",0);
                retJson.put("msg", "success");
            }catch (Exception e){
                System.out.println(e);
                retJson.put("code",-1);
                retJson.put("msg", "failed");
            }
        }
        return retJson;
    }

    //更新数据
    @GetMapping("/admin/roless")
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
    //更新role表格数据
    @GetMapping("/admin/roles")
    public HashMap<String,Object> getRoles(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
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
            List<Role> list = roleMapper.getEmpsByRange(start,limit);
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

    //批量删除Roles
    @DeleteMapping("/admin/role")
    public Object deleteRoles(@RequestBody List<Integer> list){
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

    //删除某个Role
    @DeleteMapping("/admin/role/{id}")
    public HashMap<String,Object> deleteRoleById(@PathVariable(value = "id")Integer id){
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

    //插入一个Role
    @PostMapping("/admin/role/")
    public HashMap<String,Object> insertRole(@RequestBody Role role){
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

    //更新某个Role
    @PutMapping("/admin/role/")
    public Object updateRole(@RequestBody Role role){
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

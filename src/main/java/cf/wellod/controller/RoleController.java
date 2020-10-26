package cf.wellod.controller;

import cf.wellod.bean.Role;
import cf.wellod.mapper.RoleMapper;
import cf.wellod.service.RoleService;
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

    @Autowired
    RoleService roleService;

//    @PostMapping("/add")
//    public Object addRole(@RequestBody Role role){
//        HashMap<String, Object> retJson = new HashMap<String, Object>();
//        //role.setCreateTime(DateUtil.getCurrDateTime());
//        if(role.getName().length() == 0 || role.getInfo().length() == 0){
//            retJson.put("code",-1);
//            retJson.put("msg", "字段不能为空");
//        }else{
//            try{
//                roleMapper.insertRole(role);
//                retJson.put("code",0);
//                retJson.put("msg", "success");
//            }catch (Exception e){
//                System.out.println(e);
//                retJson.put("code",-1);
//                retJson.put("msg", "failed");
//            }
//        }
//        return retJson;
//    }

    //获取所有role
    @GetMapping("/admin/roless")
    public HashMap<String,Object> getRolesTwo(){
        return roleService.getRolesTwo();
    }

    //获取一页role
    @GetMapping("/admin/roles")
    public HashMap<String,Object> getRoles(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return roleService.getRoles(page,limit);
    }

    //批量删除Roles
    @DeleteMapping("/admin/role")
    public Object deleteRoles(@RequestBody List<Integer> list){
        return roleService.deleteRoles(list);

    }

    //删除某个Role
    @DeleteMapping("/admin/role/{id}")
    public HashMap<String,Object> deleteRoleById(@PathVariable(value = "id")Integer id){
        return roleService.deleteRoleById(id);
    }

    //插入一个Role
    @PostMapping("/admin/role/")
    public HashMap<String,Object> insertRole(@RequestBody Role role){
        return roleService.insertRole(role);
    }

    //更新某个Role
    @PutMapping("/admin/role/")
    public Object updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }
}

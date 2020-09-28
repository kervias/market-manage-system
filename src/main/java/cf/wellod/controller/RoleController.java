package cf.wellod.controller;

import cf.wellod.bean.Role;
import cf.wellod.mapper.RoleMapper;
import cf.wellod.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;


@Controller
@ResponseBody
@RequestMapping("/api/admin/role")
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
}

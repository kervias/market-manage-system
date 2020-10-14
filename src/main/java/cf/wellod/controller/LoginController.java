package cf.wellod.controller;


import cf.wellod.bean.Employee;
import cf.wellod.mapper.EmployeeMapper;
import cf.wellod.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ResponseBody
@Controller
public class LoginController {

    @Autowired
    EmployeeMapper employeeMapper;

    @PostMapping("/admin/login")
    public Object login(@RequestBody Employee employee, HttpServletRequest request){
        HashMap<String, Object> retJson = new HashMap<String, Object>();
        try{
            Employee emp = employeeMapper.getEmployeeByEmail(employee.getEmail());
            if(emp == null || !MD5Util.isCompete(emp.getPassword(),employee.getPassword(), emp.getSalt())){
                retJson.put("code", -1);
                retJson.put("msg", "错误的邮箱名或密码");
                return retJson;
            }
            if(emp.getForbidden()){
                retJson.put("code", -1);
                retJson.put("msg", "账户已被禁用");
                return retJson;
            }

            request.getSession().setAttribute("eid", emp.getId());
            request.getSession().setAttribute("username", emp.getUsername());
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("redirect","/admin/index.html");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "服务器异常");
        }
        return retJson;
    }

    @GetMapping("/admin/logout")
    public Object logout(HttpServletRequest request){
        HashMap<String, Object> retJson = new HashMap<String, Object>();
        try{
            if(request.getSession().getAttribute("eid") == null){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
                return retJson;
            }
            System.out.println(request.getSession().getAttribute("eid"));
            request.getSession().removeAttribute("eid");
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("redirect","/admin/login.html");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "服务器异常");
        }
        return retJson;
    }
}

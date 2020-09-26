package cf.wellod.controller;


import cf.wellod.bean.Employee;
import cf.wellod.mapper.EmployeeMapper;
import cf.wellod.utils.DateUtil;
import cf.wellod.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@ResponseBody
@Controller
@RequestMapping("/api/admin/employee")
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @PostMapping("/add")
    public Object addEmployee(@RequestBody Employee employee) {
        HashMap<String, Object> retJson = new HashMap<String, Object>();
        if(MD5Util.convertPwd(employee)){
            if(employee.getName().length() == 0 || employee.getBirth() == null || employee.getRid() <= 0 || employee.getTel().length() == 0 || employee.getUsername().length() == 0){
                retJson.put("code",-1);
                retJson.put("msg", "字段不合法");
            }else{
                try{
                   // employee.setCreateTime(DateUtil.getCurrDateTime());
                    System.out.println(employee);
                    employeeMapper.insertEmployee(employee);
                    retJson.put("code",0);
                    retJson.put("msg", "success");
                }catch (Exception e){
                    System.out.println(e);
                    retJson.put("code",-1);
                    retJson.put("msg", "failed");
                }
            }
        }else{
            retJson.put("code",-1);
            retJson.put("msg", "密码不能为空");
        }
        return retJson;
    }
}

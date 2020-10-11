package cf.wellod.controller;


import cf.wellod.bean.Employee;
import cf.wellod.mapper.EmployeeMapper;
//import cf.wellod.utils.DateUtil;
import cf.wellod.service.EmployeeService;
import cf.wellod.utils.MD5Util;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ResponseBody
@Controller
//@RequestMapping("/api/admin/employee")
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/admin/add")
    public Object addEmployee(@RequestBody Employee employee) {
        HashMap<String, Object> retJson = new HashMap<String, Object>();
        if(MD5Util.convertPwd(employee)){
            if(employee.getName().length() == 0  || employee.getTel().length() == 0 || employee.getUsername().length() == 0){
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

    //更新表中数据
    @GetMapping("/admin/employees")
    public Object getEmployees(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return employeeService.getEmployees(page,limit);
    }

    //批量删除数据
    @DeleteMapping("/admin/employee")
    public Object deleteEmployees(@RequestBody List<Integer> list){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(list.size() != 0)
            {
                employeeMapper.deleteEmployees(list);
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

    @DeleteMapping("/admin/employee/{id}")
    public HashMap<String,Object> deleteEmployeeById(@PathVariable(value = "id")Integer id){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            employeeMapper.deleteEmployeeById(id);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    //插入一条数据
    @PostMapping("/admin/employee/")
    public HashMap<String,Object> insertEmployee(@RequestBody Employee employee){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        MD5Util.convertPwd(employee);

        System.out.println(employee);
        //System.out.println(employee);
        //System.out.println(employee.getClass());
        //System.out.println(employee[1]);
        ;
        if(employee.getName() != null  && employee.getId() == null && employee.getTel() != null
            && employee.getUsername() != null && employee.getPassword() != null && employee.getEmail() != null)
        {
            retJson.put("code", 0);
            retJson.put("msg", "操作成功");
            try{
                employeeMapper.insertEmployee(employee);
            }catch (Exception e){
                System.out.println(e);
                retJson.put("code", -1);
                retJson.put("msg", "操作失败");
            }
        }else{
            System.out.println("nijao");
            retJson.put("code", -1);
            retJson.put("msg", "操作失败");
        }

        return retJson;
    }

    //编辑一条数据
    @PutMapping("/admin/employee/")
    public Object updateEmployee(@RequestBody Employee employee){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        MD5Util.convertPwd(employee);
        try{
            employeeMapper.updateEmployee(employee);
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

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

//    @PostMapping("/admin/add")
//    public Object addEmployee(@RequestBody Employee employee) {
//        HashMap<String, Object> retJson = new HashMap<String, Object>();
//        if(MD5Util.convertPwd(employee)){
//            if(employee.getName().length() == 0  || employee.getTel().length() == 0 || employee.getUsername().length() == 0){
//                retJson.put("code",-1);
//                retJson.put("msg", "字段不合法");
//            }else{
//                try{
//                   // employee.setCreateTime(DateUtil.getCurrDateTime());
//                    System.out.println(employee);
//                    employeeMapper.insertEmployee(employee);
//                    retJson.put("code",0);
//                    retJson.put("msg", "success");
//                }catch (Exception e){
//                    System.out.println(e);
//                    retJson.put("code",-1);
//                    retJson.put("msg", "failed");
//                }
//            }
//        }else{
//            retJson.put("code",-1);
//            retJson.put("msg", "密码不能为空");
//        }
//        return retJson;
//    }

    //获取某页员工信息
    @GetMapping("/admin/employees")
    public Object getEmployees(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return employeeService.getEmployees(page,limit);
    }

    //批量删除
    @DeleteMapping("/admin/employee")
    public Object deleteEmployees(@RequestBody List<Integer> list){
        return employeeService.deleteEmployees(list);
    }

    //删除一个记录
    @DeleteMapping("/admin/employee/{id}")
    public HashMap<String,Object> deleteEmployeeById(@PathVariable(value = "id")Integer id){
        return employeeService.deleteEmployeeById(id);
    }

    //插入一条数据
    @PostMapping("/admin/employee/")
    public HashMap<String,Object> insertEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    //编辑一条数据
    @PutMapping("/admin/employee/")
    public Object updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }
}

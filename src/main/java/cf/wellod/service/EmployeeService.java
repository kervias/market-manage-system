package cf.wellod.service;

import cf.wellod.bean.Employee;
import cf.wellod.bean.Role;
import cf.wellod.mapper.EmployeeMapper;
import cf.wellod.mapper.RoleMapper;
import cf.wellod.utils.CommonUtil;
import cf.wellod.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RoleMapper roleMapper;

    public Object getEmployees(Integer page,Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = employeeMapper.getEmployeesCount();
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
            List<Employee> list_1 = employeeMapper.getEmpsByRange(start,limit);
            List<Object> list = new ArrayList<>();
            Map<String,Object> retData = new HashMap<String,Object>();
            Role role;
            for(Employee employee : list_1){
                retData = CommonUtil.getFiledInfo(employee);
                role = roleMapper.selectRole(employee.getRid());
                retData.put("rolename",role.getName());
                list.add(retData);
            }

            retJson.put("data", list);
            System.out.println(list);
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<Employee>());
        }

        return retJson;
    }

    public Object deleteEmployees(List<Integer> list){
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

    public HashMap<String,Object> deleteEmployeeById(Integer id){
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

    public HashMap<String,Object> insertEmployee(Employee employee){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        MD5Util.convertPwd(employee);
        if(employee.getName() != null  && employee.getId() == null && employee.getTel() != null
                && employee.getUsername() != null && employee.getPassword() != null && employee.getEmail() != null)
        {
            retJson.put("code", 0);
            retJson.put("msg", "success");
            try{
                employeeMapper.insertEmployee(employee);
            }catch (Exception e){
                System.out.println(e);
                retJson.put("code", -1);
                retJson.put("msg", "failed");
            }
        }else{
            System.out.println("nijao");
            retJson.put("code", -1);
            retJson.put("msg", "invalid");
        }

        return retJson;
    }

    public Object updateEmployee(Employee employee){
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

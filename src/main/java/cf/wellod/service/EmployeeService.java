package cf.wellod.service;

import cf.wellod.bean.Employee;
import cf.wellod.bean.Role;
import cf.wellod.mapper.EmployeeMapper;
import cf.wellod.mapper.RoleMapper;
import cf.wellod.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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
}

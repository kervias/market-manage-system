package cf.wellod.controller;


import cf.wellod.mapper.BaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


// 全局信息
@Controller
@ResponseBody
@RequestMapping("/common")
public class BaseInfoController {

    @Autowired
    private BaseInfoMapper baseInfoMapper;

    // 获取系统配置信息
    @GetMapping("/sysinfo")
    public Object getSystemInfo(){
        HashMap<String, Object> retJson = new HashMap<String, Object>();

        HashMap<String, String> sysJson= new HashMap<String,String>();
        sysJson.put("JAVA版本",System.getProperty("java.version"));
        sysJson.put("操作系统",System.getProperty("os.name"));
        sysJson.put("Spring版本",SpringVersion.getVersion());
        sysJson.put("SpringBoot版本",SpringBootVersion.getVersion());
        String databaseInfo = baseInfoMapper.getVersion();
        if(databaseInfo.contains("SQL Server")){
            databaseInfo = databaseInfo.split(" -")[0];
        }
        sysJson.put("数据库",databaseInfo);
        retJson.put("code",0);
        retJson.put("msg", "success");
        retJson.put("data", sysJson);
        return retJson;
    }


    @GetMapping("/userinfo")
    public Object getUserInfo(HttpServletRequest request){
        HashMap<String, Object> retJson = new HashMap<String, Object>();
        try{
            String eid = request.getSession().getAttribute("eid").toString();
            String name = request.getSession().getAttribute("username").toString();
            retJson.put("eid", eid);
            retJson.put("username", name);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "服务器异常");
        }
        return retJson;
    }

}

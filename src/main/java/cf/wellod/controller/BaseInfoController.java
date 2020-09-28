package cf.wellod.controller;


import cf.wellod.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


// 全局信息
@Controller
@ResponseBody
@RequestMapping("/common")
public class Common {

    @Autowired
    private InfoMapper infoMapper;

    // 获取系统配置信息
    @GetMapping("/sysinfo")
    public Object getSystemInfo(){
        HashMap<String, Object> retJson = new HashMap<String, Object>();

        HashMap<String, String> sysJson= new HashMap<String,String>();
        sysJson.put("JAVA版本",System.getProperty("java.version"));
        sysJson.put("操作系统",System.getProperty("os.name"));
        sysJson.put("Spring版本",SpringVersion.getVersion());
        sysJson.put("SpringBoot版本",SpringBootVersion.getVersion());
        String databaseInfo = infoMapper.getVersion();
        if(databaseInfo.contains("SQL Server")){
            databaseInfo = databaseInfo.split(" -")[0];
        }
        sysJson.put("数据库",databaseInfo);
        retJson.put("code",0);
        retJson.put("msg", "success");
        retJson.put("data", sysJson);
        return retJson;
    }

}

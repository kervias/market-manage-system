package cf.wellod.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ResponseBody
@Controller
public class LoginController {

    @PostMapping("/admin/login")
    public Object login(@RequestBody HashMap<String,Object> reqJson){
        HashMap<String, Object> retJson = new HashMap<String, Object>();

        return retJson;
    }
}

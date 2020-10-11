package cf.wellod.controller;


import cf.wellod.utils.CommonUtil;
import cf.wellod.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @ResponseBody
    @GetMapping("/md5/{pwd}")
    public Object getmd5(@PathVariable("pwd")String pwd){
        return MD5Util.string2MD5(pwd);
    }

    @ResponseBody
    @GetMapping("/goodsID/{id}")
    public Object getGoodsId(@PathVariable("id")String id){
        return CommonUtil.generateGoodsIdByBatch(id);
    }
}

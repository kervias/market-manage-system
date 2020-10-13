package cf.wellod.controller;

import cf.wellod.service.OutBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
@RequestMapping("/admin")
public class OutBoundController {
    @Autowired
    OutBoundService outBoundService;
    // 请求一页数据

    @GetMapping("/outbounds_range")
    public Object getInBoundsByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return outBoundService.getInBoundsByRange(page, limit);
    }
}

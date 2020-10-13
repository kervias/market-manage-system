package cf.wellod.controller;

import cf.wellod.service.InBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
@RequestMapping("/admin")
public class InBoundController {

    @Autowired
    InBoundService inBoundService;
    // 请求一页数据
    @GetMapping("/inbounds_range")
    public Object getInBoundsByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return inBoundService.getInBoundsByRange(page, limit);
    }
}

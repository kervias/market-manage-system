package cf.wellod.controller;

import cf.wellod.bean.GoodsInfo;
import cf.wellod.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class GoodsInfoController {
    @Autowired
    GoodsInfoService goodsInfoService;
    // 添加一条记录
    @PostMapping("/goodsInfo")
    public Object addGoodsInfo(@RequestBody GoodsInfo goodsInfo){
        return goodsInfoService.addGoodsInfo(goodsInfo);
    }

    // 删除一条记录
    @DeleteMapping("/goodsInfo/{id}")
    public Object deleteGoodsInfoById(@PathVariable(value = "id")String EN13){
        return goodsInfoService.deleteGoodsInfoById(EN13);
    }

    // 批量删除一些记录
    @DeleteMapping("/goodsInfos")
    public Object deleteGoodsInfosBatch(@RequestBody List<String> list){
        return goodsInfoService.deleteGoodsInfosBatch(list);
    }

    // 修改某个记录
    @PutMapping("/goodsInfo")
    public Object updateGoodsInfo(@RequestBody GoodsInfo goodsInfo){
        return goodsInfoService.updateGoodsInfo(goodsInfo);
    }

    // 查询某页记录
    @GetMapping("/goodsInfos_range")
    public Object getGoodsInfosByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return goodsInfoService.getGoodsInfosByRange(page, limit);
    }

    // 查询所有记录
    @GetMapping("/goodsInfos_all")
    public Object getGoodsInfosAll(){
        return goodsInfoService.getGoodsInfosAll();
    }

    // 查询一条记录
    @GetMapping("/goodsInfo/{id}")
    public Object getGoodsInfoById(@PathVariable(value = "id")String EN13){
        return goodsInfoService.getGoodsInfoById(EN13);
    }
}

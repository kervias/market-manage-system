package cf.wellod.controller;


import cf.wellod.bean.Goods;
import cf.wellod.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    //添加一条记录
    @PostMapping("/admin/goods")
    public Object addGoods(@RequestBody Goods goods){
        //System.out.println(goods);
        //return null;
        return goodsService.addGoods(goods);
    }

    //删除一条记录
    @DeleteMapping("/admin/goods/{id}")
    public Object deleteGoodsById(@PathVariable(value = "id")String id){
        return goodsService.deleteGoodsById(id);
    }

    //批量删除记录
    @DeleteMapping("/admin/goodses")
    public Object deleteGoodsesBatch(@RequestBody List<String> list){
        return goodsService.deleteGoodsesBatch(list);
    }

    //修改某个记录
    @PutMapping("/admin/goods")
    public Object updateGoods(@RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }

    //查询某页记录
    @GetMapping("/admin/goodses_range")
    public Object getGoodsesByRange(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit){
        return goodsService.getGoodsesByRange(page,limit);
    }

    //查询所有记录
    @GetMapping("/admin/goodses_all")
    public Object getGoodsAll(){
        return goodsService.getGoodsesAll();
    }

    //查询单条记录
    @GetMapping("/admin/goods/{id}")
    public Object getGoodsById(@PathVariable(value = "id")String id){
        return goodsService.getGoodsByID(id);
    }

}

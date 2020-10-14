package cf.wellod.controller;

import cf.wellod.bean.InBound;
import cf.wellod.bean.OutBound;
import cf.wellod.bean.Stock;
import cf.wellod.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class StockController {

    @Autowired
    StockService stockService;

    // 新入库请求
    @PostMapping("/stock")
    public Object inBoundNewStock(@RequestBody InBound inBound){
        return stockService.inBoundNewStock(inBound);
    }

    // 入库请求
    @PutMapping("/stock/in")
    public Object inBoundStock(@RequestBody InBound inBound){
        return stockService.inBoundStock(inBound);
    }

    // 出库请求
    @PutMapping("/stock/out")
    public Object outBoundStock(@RequestBody OutBound outBound) {
        return stockService.outBoundStock(outBound);
    }

    // 删除Stock记录
    @DeleteMapping("/stock")
    public Object deleteStock(@RequestBody Stock stock){
        return stockService.deleteStock(stock);
    }

    // 编辑阈值
    @PutMapping("/stock/threshold")
    public Object updateStockThreshold(@RequestBody Stock stock){
        return stockService.updateStockThreshold(stock);
    }

    // 查询记录 By Range
    @GetMapping("/stocks_range")
    public Object getStockByRange(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit){
        return stockService.getStockByRange(page, limit);
    }

    // 查询记录 All
    @GetMapping("/stocks_all")
    public Object getStockAll(){
        return stockService.getStockAll();
    }

    @GetMapping("/stocks_exp")
    public HashMap<String, Object> getStockThresholdRange(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit) {
        return stockService.getStockThresholdRange(page, limit);
    }
}

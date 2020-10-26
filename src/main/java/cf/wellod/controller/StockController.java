package cf.wellod.controller;

import cf.wellod.bean.InBound;
import cf.wellod.bean.OutBound;
import cf.wellod.bean.Stock;
import cf.wellod.mapper.StockMapper;
import cf.wellod.service.StockService;
import cf.wellod.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class StockController {

    @Autowired
    StockService stockService;

    @Autowired
    StockMapper stockMapper;

    // 新入库请求
    @PostMapping("/stock")
    public Object inBoundNewStock(@RequestBody InBound inBound, HttpServletRequest request){
        inBound.setEid(Integer.valueOf(request.getSession().getAttribute("eid").toString()));
        return stockService.inBoundNewStock(inBound);
    }

    // 入库请求
    @PutMapping("/stock/in")
    public Object inBoundStock(@RequestBody InBound inBound, HttpServletRequest request){
        inBound.setEid(Integer.valueOf(request.getSession().getAttribute("eid").toString()));
        return stockService.inBoundStock(inBound);
    }

    // 出库请求
    @PutMapping("/stock/out")
    public Object outBoundStock(@RequestBody OutBound outBound, HttpServletRequest request) {
        outBound.setEid(Integer.valueOf(request.getSession().getAttribute("eid").toString()));
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

    @ResponseBody
    @PostMapping("/stock/upload")
    public Object batchInsert(MultipartFile file) {
        HashMap<String,Object> retJson = new HashMap<>();
        try {
            CsvUtil csvUtil = new CsvUtil();
            // 将csv文件内容转成bean
            List<Stock> csvData = csvUtil.getCsvData(file, Stock.class);
            if (csvData == null || csvData.size() == 0) {
                retJson.put("code", -1);
                retJson.put("msg","resolve csv file failed");
                return retJson;
            }

            Stock tmpStock;
            for(Stock stock : csvData){
                tmpStock = stockMapper.getStockByPrimaryKey(stock.getGid(), stock.getWid());
                if(tmpStock != null)
                    stock.setThreshold(stock.getQuantity() - tmpStock.getQuantity());
            }
            retJson.put("code", 0);
            retJson.put("msg","success");
            retJson.put("data", csvData);
            retJson.put("count", csvData.size());
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg","failed");
        }
        return retJson;
    }
}

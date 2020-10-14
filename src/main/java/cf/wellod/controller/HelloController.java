package cf.wellod.controller;


import cf.wellod.bean.Stock;
import cf.wellod.mapper.StockMapper;
import cf.wellod.utils.CommonUtil;
import cf.wellod.utils.CsvUtil;
import cf.wellod.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class HelloController {


    @Autowired
    StockMapper stockMapper;

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

    @ResponseBody
    @GetMapping("/sss")
    public Object getDateString(){return CommonUtil.generateGoodsBatchByProdDate(new Date());}

    @ResponseBody
    @PostMapping("/upload")
    public Object batchInsert(MultipartFile file) {
        HashMap<String,Object> retJson = new HashMap<>();
        try {
            CsvUtil csvUtil = new CsvUtil();
            // 将csv文件内容转成bean
            List<Stock> csvData = csvUtil.getCsvData(file, Stock.class);
            if (csvData == null || csvData.size() == 0) {
                retJson.put("code", "-1");
                retJson.put("msg","resolve csv file failed");
                return retJson;
            }

            Stock tmpStock;
            for(Stock stock : csvData){
                tmpStock = stockMapper.getStockByPrimaryKey(stock.getGid(), stock.getWid());
                if(tmpStock != null)
                    stock.setThreshold(stock.getQuantity() - tmpStock.getQuantity());
            }
            retJson.put("code", "0");
            retJson.put("msg","success");
            retJson.put("data", csvData);
        }catch (Exception e){
            retJson.put("code", "-1");
            retJson.put("msg","failed");
        }
        return retJson;
    }
}

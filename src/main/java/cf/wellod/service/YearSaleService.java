package cf.wellod.service;

import cf.wellod.bean.YearSale;
import cf.wellod.mapper.YearSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class YearSaleService {
    @Autowired
    YearSaleMapper yearSaleMapper;

    public HashMap<String,Object> getYearSalesRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = yearSaleMapper.getYearSalesCount();
        try{
            if(count >= 0 && limit > 0 && page > 0){
                int pageNum = (int)Math.ceil(count.floatValue()/limit);
                if(pageNum == 0) pageNum++;
                if(page > pageNum) page = pageNum;
                int start = (page-1)*limit+1;
                System.out.println("start "+start);
                retJson.put("code", 0);
                retJson.put("msg", "success");
                retJson.put("count", count);
                List<YearSale> list = yearSaleMapper.getYearSalesByRange(start, limit);
                retJson.put("data", list);
                System.out.println(list);
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
                retJson.put("count", 0);
                retJson.put("data", new ArrayList<YearSale>());
            }
        }catch (Exception e){
            retJson.put("code",-1);
            retJson.put("msg","failed");
            retJson.put("count",0);
            retJson.put("data",new ArrayList<YearSale>());
        }
        return retJson;
    }
}

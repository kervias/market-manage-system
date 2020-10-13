package cf.wellod.service;

import cf.wellod.bean.InBound;
import cf.wellod.bean.OutBound;
import cf.wellod.bean.Stock;
import cf.wellod.mapper.InBoundMapper;
import cf.wellod.mapper.OutBoundMapper;
import cf.wellod.mapper.StockMapper;
import cf.wellod.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StockService {
    @Autowired
    InBoundMapper inBoundMapper;

    @Autowired
    OutBoundMapper outBoundMapper;

    @Autowired
    StockMapper stockMapper;

    // 新商品入库请求
    @Transactional
    public HashMap<String, Object> inBoundNewStock(InBound inBound){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(inBound.getEid() == null || inBound.getGid() == null || inBound.getQuantity() == null || inBound.getWid() == null || inBound.getThreshold() == null || inBound.getQuantity() < 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                if(stockMapper.isExistsRecord(inBound.getGid(),inBound.getWid()) == 0){
                    stockMapper.addInBoundStock(inBound); // 插入Stock表
                    inBound.setOp_time(DateUtil.getCurrDateTime());
                    inBoundMapper.addInBoundRecord(inBound); // 插入InBound表
                    retJson.put("code", 0);
                    retJson.put("msg", "success");
                }else{
                    retJson.put("code", -1);
                    retJson.put("msg", "invalid");
                }

            }
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 入库请求
    @Transactional
    public HashMap<String, Object> inBoundStock(InBound inBound){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(inBound.getEid() == null || inBound.getGid() == null || inBound.getQuantity() == null || inBound.getWid() == null || inBound.getQuantity() < 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                if(stockMapper.isExistsRecord(inBound.getGid(),inBound.getWid()) == 0){
                    retJson.put("code", -1);
                    retJson.put("msg", "invalid");
                }else{
                    stockMapper.inBoundStock(inBound); // 修改Stock表
                    inBound.setOp_time(DateUtil.getCurrDateTime());
                    inBoundMapper.addInBoundRecord(inBound); // 插入InBound表
                    retJson.put("code", 0);
                    retJson.put("msg", "success");
                }
            }
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 出库请求
    @Transactional
    public HashMap<String, Object> outBoundStock(OutBound outBound){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(outBound.getEid() == null || outBound.getGid() == null || outBound.getQuantity() == null || outBound.getWid() == null || outBound.getReason() == null || outBound.getQuantity() <= 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                if(stockMapper.isExistsRecord(outBound.getGid(),outBound.getWid()) == 0){
                    retJson.put("code", -1);
                    retJson.put("msg", "invalid: not exist in table");
                }else{
                    Stock stock = stockMapper.getStockByPrimaryKey(outBound.getGid(),outBound.getWid());
                    if(stock.getQuantity() < outBound.getQuantity()){
                        retJson.put("code", -1);
                        retJson.put("msg", "invalid: over outbound");
                    }else{
                        stockMapper.outBoundStock(outBound); // 修改Stock表
                        outBound.setOpTime(DateUtil.getCurrDateTime());
                        outBoundMapper.addOutBoundRecord(outBound); // 插入outBound表
                        retJson.put("code", 0);
                        retJson.put("msg", "success");
                    }
                }
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    @Transactional
    // 删除记录请求
    public HashMap<String, Object> deleteStock(Stock stock){
        Stock stock_temp;
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(stock.getGid() == null || stock.getWid() == null)
            {
                retJson.put("code", -1);
                retJson.put("msg", "invalid: null");
            }else{
                stock_temp = stockMapper.getStockByPrimaryKey(stock.getGid(),stock.getWid());
                if(stock_temp.getQuantity().equals(0)){
                    retJson.put("code", -1);
                    retJson.put("msg", "invalid: quantity not equal to 0");
                }else{
                    stockMapper.deleteStockByPrimaryKey(stock.getGid(),stock.getWid());
                    retJson.put("code", 0);
                    retJson.put("msg", "success");
                }
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }


    // 编辑阈值
    public HashMap<String, Object> updateStockThreshold(Stock stock){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(stock.getGid() == null || stock.getWid() == null || stock.getThreshold() < 0)
            {
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                stockMapper.updateThreshold(stock);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 获取某页数据
    public HashMap<String,Object> getStockByRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = stockMapper.getStockCount();
        if(count >= 0 && limit > 0 && page > 0)
        {
            int pageNum = (int)Math.ceil(count.floatValue()/limit);
            if(pageNum == 0) pageNum++;
            if(page > pageNum) page = pageNum;
            int start = (page-1)*limit+1;
            //System.out.println("start "+start);
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("count", count);
            List<Stock> list = stockMapper.getStockByRange(start,limit);
            retJson.put("data", list);
            //System.out.println(list);
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<Stock>());
        }

        return retJson;
    }

    // 获取全部数据
    public HashMap<String,Object> getStockAll(){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("data", stockMapper.getStockAll());

        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

}

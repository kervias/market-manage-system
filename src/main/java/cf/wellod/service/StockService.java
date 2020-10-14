package cf.wellod.service;

import cf.wellod.bean.Goods;
import cf.wellod.bean.InBound;
import cf.wellod.bean.OutBound;
import cf.wellod.bean.Stock;
import cf.wellod.mapper.GoodsMapper;
import cf.wellod.mapper.InBoundMapper;
import cf.wellod.mapper.OutBoundMapper;
import cf.wellod.mapper.StockMapper;
import cf.wellod.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    GoodsMapper goodsMapper;

    // 新商品入库请求
    @Transactional
    public HashMap<String, Object> inBoundNewStock(InBound inBound){
        HashMap<String,Object> retJson = new HashMap<>();
        System.out.println(inBound);
        inBound.setEid(1);
        try{
            if(inBound.getEid() == null || inBound.getGid() == null || inBound.getQuantity() == null || inBound.getWid() == null || inBound.getThreshold() == null || inBound.getQuantity() < 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                if(stockMapper.isExistsRecord(inBound.getGid(),inBound.getWid()) == 0){
                    stockMapper.addInBoundStock(inBound); // 插入Stock表
                    inBound.setOpTime(DateUtil.getCurrDateTime());
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
        inBound.setEid(1);
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
                    inBound.setOpTime(DateUtil.getCurrDateTime());
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
        outBound.setEid(1);
        try{
            if(outBound.getEid() == null || outBound.getGid() == null || outBound.getQuantity() == null
                    || outBound.getWid() == null || outBound.getQuantity() <= 0 || outBound.getReason() < 0 || outBound.getReason() > 1){
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
                        if(outBound.getReason() == 1)
                            goodsMapper.addGoodsShelfCount(outBound.getGid(),outBound.getQuantity()); //放入货架
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
        System.out.println(stock);
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(stock.getGid() == null || stock.getWid() == null)
            {
                retJson.put("code", -1);
                retJson.put("msg", "invalid: null");
            }else{
                stock_temp = stockMapper.getStockByPrimaryKey(stock.getGid(),stock.getWid());
                System.out.println(stock_temp);
                if(stock_temp.getQuantity() != 0){
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

    // 报警阈值
    public HashMap<String, Object> getStockThresholdRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Date tmpDate;
        Goods tmpGoods;
        Date currDate = new Date();
        try{
            List<Stock> allStock = stockMapper.getStockAll();
            List<Stock> dataStock = new ArrayList<Stock>();
            for(Stock stock : allStock){
                if(stock.getThreshold() > stock.getQuantity()){
                    tmpGoods = goodsMapper.getGoodsById(stock.getGid());
                    tmpDate = DateUtil.getDateAfterAddMonth(tmpGoods.getProdDate(), tmpGoods.getExpDate());
                    System.out.println(tmpDate);
                    if(tmpDate.compareTo(currDate) > 0){
                        dataStock.add(stock);
                    }
                }
            }
            Integer count = dataStock.size();
            System.out.println(dataStock);
            if(count >= 0 && limit > 0 && page > 0)
            {
                int pageNum = (int)Math.ceil(count.floatValue()/limit);
                if(pageNum == 0) pageNum++;
                if(page > pageNum) page = pageNum;
                int start = (page-1)*limit;
                retJson.put("code", 0);
                retJson.put("msg", "success");
                retJson.put("count", count);
                if(limit > count) limit = count;
                List<Stock> list = dataStock.subList(start, limit);
                retJson.put("data", list);
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "failed");
                retJson.put("count", 0);
                retJson.put("data", new ArrayList<Stock>());
            }
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<Stock>());
        }
        return retJson;
    }
}

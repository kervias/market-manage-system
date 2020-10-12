package cf.wellod.service;

import cf.wellod.bean.Goods;
import cf.wellod.mapper.GoodsMapper;
import cf.wellod.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Transactional
    public HashMap<String,Object> addGoods(Goods goods){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try {
            if(goods.getEN13() == null || goods.getExpDate() == null || goods.getUnit() == null
                    || goods.getBuyPrice() == null || goods.getPrice() == null || goods.getDiscount() > 1
                    || goods.getDiscount() < 0
            ){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                String batchstring = CommonUtil.generateGoodsBatchByProdDate(goods.getProdDate());
                goods.setBatch(batchstring);
                goods.setId(CommonUtil.generateGoodsIdByBatch(goods.getBatch()));
                //查询该ID在表中是否存在
                Goods goods_1;
                do{
                    goods.setId(CommonUtil.generateGoodsIdByBatch(goods.getBatch()));
                    goods_1 = goodsMapper.getGoodsById(goods.getId());
                }while(goods_1 != null);
                goodsMapper.addGoods(goods);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String,Object> getGoodsByID(String id){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            List<Goods> list = new ArrayList<Goods>();
            list.add(goodsMapper.getGoodsById(id));
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("data",list);
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    @Transactional
    public HashMap<String,Object> deleteGoodsById(String id){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{

            goodsMapper.deleteGoodsById(id);
            retJson.put("code", 0);
            retJson.put("msg", "success");

        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    @Transactional
    public HashMap<String,Object> deleteGoodsesBatch(List<String> list){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(list.size() == 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                goodsMapper.deleteGoodsesBatch(list);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    @Transactional
    public HashMap<String,Object> updateGoods(Goods goods){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try {
            if(goods.getEN13() == null || goods.getExpDate() == null || goods.getUnit() == null
                    || goods.getBuyPrice() == null || goods.getPrice() == null || goods.getDiscount() > 1
                    || goods.getDiscount() < 0
            ){
                System.out.println(goods);
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{

                goodsMapper.updateGoods(goods);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String, Object> getGoodsesByRange(Integer page,Integer limit){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        Integer count = goodsMapper.getGoodsesCount();
        if(count >= 0 && limit > 0 && page > 0)
        {
            int pageNum = (int)Math.ceil(count.floatValue()/limit);
            if(pageNum == 0) pageNum = 1;
            if(page > pageNum)page = pageNum;
            int start = (page-1)*limit+1;
            System.out.println("start "+start);
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("count", count);
            List<Goods> list = goodsMapper.getGoodsesByRange(start,limit);

            retJson.put("data", list);
            System.out.println(list);
        }else{
            retJson.put("code", 0);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<Goods>());
        }
        return retJson;
    }

    public HashMap<String,Object> getGoodsesAll(){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            List<Goods> list = goodsMapper.getGoodsesAll();
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("data",list);
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }
}

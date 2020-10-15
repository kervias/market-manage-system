package cf.wellod.service;

import cf.wellod.bean.WareHouse;
import cf.wellod.mapper.WareHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WareHouseService {

    @Autowired
    WareHouseMapper wareHouseMapper;

    public HashMap<String,Object> getWareHouseByRange(Integer page,Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = wareHouseMapper.getWareHousesCount();
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
                List<WareHouse> list = wareHouseMapper.getWareHousesByRange(start,limit);
                retJson.put("data", list);
                System.out.println(list);
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
                retJson.put("count", 0);
                retJson.put("data", new ArrayList<WareHouse>());
            }
        }catch (Exception e){
            retJson.put("code",-1);
            retJson.put("msg","failed");
            retJson.put("count",0);
            retJson.put("data",new ArrayList<WareHouse>());
        }

        return retJson;
    }

    public HashMap<String,Object> addWareHouse(WareHouse wareHouse){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(wareHouse.getId() == null && wareHouse.getName() != null && wareHouse.getInfo() != null
               && wareHouse.getAddress() != null){
                retJson.put("code", 0);
                retJson.put("msg", "success");
                wareHouseMapper.addWareHouse(wareHouse);
            }else{
                retJson.put("code",-1);
                retJson.put("msg","invalid");
            }
        }catch (Exception e){
            retJson.put("code",-1);
            retJson.put("msg","failed");
        }

        return retJson;
    }

    public HashMap<String,Object> updateWareHouse(WareHouse wareHouse){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(wareHouse.getId() != null && wareHouse.getName() != null && wareHouse.getInfo() != null
                    && wareHouse.getAddress() != null){
                retJson.put("code", 0);
                retJson.put("msg", "success");
                wareHouseMapper.updateWareHouse(wareHouse);
            }else{
                retJson.put("code",-1);
                retJson.put("msg","invalid");
            }
        }catch (Exception e){
            retJson.put("code",-1);
            retJson.put("msg","failed");
        }

        return retJson;
    }

    public HashMap<String,Object> deleteWareHouseById(Integer id){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            wareHouseMapper.deleteWareHouseById(id);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String,Object> deleteWareHousesBatch(List<Integer> list){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(list.size() != 0)
            {
                wareHouseMapper.deleteWareHouseBatch(list);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String,Object> getWareHousesAll(){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            retJson.put("code", 0);
            retJson.put("msg", "success");
            //retJson.put("count", count);
            List<WareHouse> list = wareHouseMapper.getWareHousesAll();
            retJson.put("data",list);
            System.out.println(list);
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("data", new ArrayList<WareHouse>());
        }
        return retJson;
    }
}

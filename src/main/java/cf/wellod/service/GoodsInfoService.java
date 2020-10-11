package cf.wellod.service;

import cf.wellod.bean.Category;
import cf.wellod.bean.GoodsInfo;
import cf.wellod.bean.Supplier;
import cf.wellod.mapper.GoodsInfoMapper;
import cf.wellod.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsInfoService {

    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    // 添加一条记录
    public HashMap<String, Object> addGoodsInfo(GoodsInfo goodsInfo){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(goodsInfo.getName() == null || goodsInfo.getCid() == null || goodsInfo.getSid() == null
                || goodsInfo.getEN13() == null || goodsInfo.getInfo() == null
            ){
                System.out.println(goodsInfo);
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                goodsInfoMapper.addGoodsInfo(goodsInfo);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 删除一条记录
    public HashMap<String, Object> deleteGoodsInfoById(String EN13){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{

            goodsInfoMapper.deleteGoodsInfoById(EN13);
            retJson.put("code", 0);
            retJson.put("msg", "success");

        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 删除多条记录
    public HashMap<String, Object> deleteGoodsInfosBatch(List<String> delIdList){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(delIdList.size() == 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                goodsInfoMapper.deleteGoodsInfosBatch(delIdList);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 修改一条记录
    public HashMap<String, Object> updateGoodsInfo(GoodsInfo goodsInfo){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(goodsInfo.getName() == null || goodsInfo.getCid() == null || goodsInfo.getSid() == null
                    || goodsInfo.getEN13() == null || goodsInfo.getInfo() == null){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                goodsInfoMapper.updateGoodsInfo(goodsInfo);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 查询记录 By Range
    @Transactional
    public HashMap<String, Object> getGoodsInfosByRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        Integer count = goodsInfoMapper.getGoodsInfosCount();
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
            List<GoodsInfo> list_1 = goodsInfoMapper.getGoodsInfosByRange(start,limit);
            List<Object> list = new ArrayList<>();
            Map<String,Object> retData;
            Supplier supplier;
            Category category;
//            for(GoodsInfo goodsInfo : list_1){
//                retData = CommonUtil.getFiledInfo(goodsInfo);
//
//
//                role = roleMapper.selectRole(employee.getRid());
//                retData.put("rolename",role.getName());
//                list.add(retData);
//            }

            retJson.put("data", list_1);
            System.out.println(list);
        }else{
            retJson.put("code", 0);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<GoodsInfo>());
        }
        return retJson;
    }

    // 查询所有记录
    public HashMap<String, Object> getGoodsInfosAll(){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            List<GoodsInfo> list = goodsInfoMapper.getGoodsInfosAll();
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("data",list);
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }


    public HashMap<String,Object> getGoodsInfoById(String EN13){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            List<GoodsInfo> list = new ArrayList<GoodsInfo>();
            list.add(goodsInfoMapper.getGoodsInfoById(EN13));
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

package cf.wellod.service;

import cf.wellod.bean.Supplier;
import cf.wellod.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierMapper supplierMapper;

    public HashMap<String,Object> addSupp(Supplier supplier){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(supplier.getName() != null && supplier.getAddress() != null && supplier.getTel() != null && supplier.getId() == null)
            {
                retJson.put("code", 0);
                retJson.put("msg", "success");
                supplierMapper.insertSupp(supplier);
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String,Object> getSuppsByRange(Integer page,Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = supplierMapper.getSuppCount();
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
                List<Supplier> list = supplierMapper.getEmpsByRange(start,limit);
                retJson.put("data", list);
                System.out.println(list);
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
                retJson.put("count", 0);
                retJson.put("data", new ArrayList<Supplier>());
            }
        }catch (Exception e){
            retJson.put("code",-1);
            retJson.put("msg","failed");
            retJson.put("count",0);
            retJson.put("data",new ArrayList<Supplier>());
        }

        return retJson;
    }

    public HashMap<String,Object> deleteSuppById(Integer id){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            supplierMapper.deleteSuppById(id);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String,Object> updateSupp(Supplier supplier){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(supplier.getId() != null && supplier.getName() != null && supplier.getAddress() != null
                    && supplier.getTel() != null){
                retJson.put("code", 0);
                retJson.put("msg", "success");
                supplierMapper.updateSupp(supplier);
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

    public HashMap<String,Object> deleteSupps(List<Integer> list){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(list.size() != 0)
            {
                supplierMapper.deleteSupps(list);
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

    public HashMap<String,Object> getSuppsAll(){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            retJson.put("code", 0);
            retJson.put("msg", "success");
            //retJson.put("count", count);
            List<Supplier> list = supplierMapper.getSuppsAll();
            retJson.put("data",list);
            System.out.println(list);
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("data", new ArrayList<Supplier>());
        }
        return retJson;
    }
}

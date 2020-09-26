package cf.wellod.controller;


import cf.wellod.bean.Supplier;
import cf.wellod.mapper.SupplierMapper;
import javafx.beans.binding.ObjectExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TestController {

    @Autowired
    SupplierMapper supplierMapper;

    // 查询某个Supplier
    @ResponseBody
    @GetMapping("/admin/supp/{id}")
    public HashMap<String,Object> getSuppById(@PathVariable("id")Integer id){
        System.out.println(id);
        Supplier supp = supplierMapper.getSuppById(id);
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        ArrayList<Supplier> arrayList = new ArrayList<Supplier>();
        if(supp == null){
            retJson.put("code", -1);
            retJson.put("msg", "没有数据");
        }else{
            retJson.put("code", 0);
            retJson.put("msg", "success");
            arrayList.add(supp);
            retJson.put("data", arrayList);
        }
        return retJson;
    }


    // 插入一个Supplier
    @ResponseBody
    @PostMapping("/admin/supp/")
    public HashMap<String,Object> addSupp(@RequestBody Supplier supplier){
        System.out.println(supplier);
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        if(supplier.getName() != null && supplier.getAddress() != null && supplier.getTel() != null && supplier.getId() == null)
        {
            retJson.put("code", 0);
            retJson.put("msg", "操作成功");
            try{
                supplierMapper.insertSupp(supplier);
            }catch (Exception e){
                System.out.println(e);
                retJson.put("code", -1);
                retJson.put("msg", "操作失败");
            }
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "操作失败");
        }
        return retJson;
    }


    // 分页查询Suppliers
    @ResponseBody
    @GetMapping("/admin/supps")
    public HashMap<String,Object> getSupps(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        Integer count = supplierMapper.getSuppCount();
        if(count >= 0 && limit > 0 && page > 0)
        {

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
            retJson.put("code", 0);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<Supplier>());
        }

        return retJson;
    }

    // 删除某个Supplier
    @ResponseBody
    @DeleteMapping("/admin/supp/{id}")
    public HashMap<String,Object> deleteSuppById(@PathVariable(value = "id")Integer id){
        System.out.println("删除Supplier, id is: "+id);
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

    // 更新某个Supplier
    @ResponseBody
    @PutMapping("/admin/supp/")
    public Object updateSupp(@RequestBody Supplier supplier){
        //System.out.println(supplier);
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            supplierMapper.updateSupp(supplier);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    // 批量删除某个Supplier
    @ResponseBody
    @DeleteMapping("/admin/supp")
    public Object deleteSupps(@RequestBody List<Integer> list){
        System.out.println(list);
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(list.size() != 0)
            {
                supplierMapper.deleteSupps(list);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }else{
                retJson.put("code", -1);
                retJson.put("msg", "操作数量不能为0");
            }
        }catch (Exception e){
            System.out.println(e);
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

}

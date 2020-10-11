package cf.wellod.controller;


import cf.wellod.bean.Supplier;
import cf.wellod.mapper.SupplierMapper;
import cf.wellod.service.SupplierService;
import javafx.beans.binding.ObjectExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SupplierController {

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    SupplierService supplierService;

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
        return supplierService.addSupp(supplier);
    }


    // 分页查询Suppliers
    @ResponseBody
    @GetMapping("/admin/supps")
    public HashMap<String,Object> getSupps(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return supplierService.getSuppsByRange(page,limit);
    }

    // 删除某个Supplier
    @ResponseBody
    @DeleteMapping("/admin/supp/{id}")
    public HashMap<String,Object> deleteSuppById(@PathVariable(value = "id")Integer id){
        return supplierService.deleteSuppById(id);
    }

    // 更新某个Supplier
    @ResponseBody
    @PutMapping("/admin/supp/")
    public Object updateSupp(@RequestBody Supplier supplier){
        //System.out.println(supplier);
        return supplierService.updateSupp(supplier);
    }

    // 批量删除某个Supplier
    @ResponseBody
    @DeleteMapping("/admin/supp")
    public Object deleteSupps(@RequestBody List<Integer> list){
        return supplierService.deleteSupps(list);
    }

    @ResponseBody
    @GetMapping("/admin/supps_all")
    public Object getSuppsAll(){
        return supplierService.getSuppsAll();
    }

}

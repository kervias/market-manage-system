package cf.wellod.controller;

import cf.wellod.bean.WareHouse;
import cf.wellod.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class WareHouseController {

    @Autowired
    WareHouseService wareHouseService;

    @GetMapping("/admin/warehouse_range")
    public Object getWareHouseByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return wareHouseService.getWareHouseByRange(page,limit);
    }

    @PostMapping("/admin/warehouse")
    public Object addWareHouse(@RequestBody WareHouse wareHouse){
        return wareHouseService.addWareHouse(wareHouse);
    }

    @PutMapping("/admin/warehouse")
    public Object updateWareHouse(@RequestBody WareHouse wareHouse){
        return wareHouseService.updateWareHouse(wareHouse);
    }

    @DeleteMapping("/admin/warehouse/{id}")
    public Object deleteWareHouseById(@PathVariable(value = "id")Integer id){
        return wareHouseService.deleteWareHouseById(id);
    }

    @DeleteMapping("/admin/warehouses")
    public Object deleteWareHousesBatch(@RequestBody List<Integer> list){
        return wareHouseService.deleteWareHousesBatch(list);
    }

    @GetMapping("/admin/warehouses_all")
    public Object getWareHousesAll(){
        return wareHouseService.getWareHousesAll();
    }
}

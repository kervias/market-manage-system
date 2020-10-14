package cf.wellod.controller;

import cf.wellod.bean.OrderList;
import cf.wellod.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@ResponseBody
@Controller
@RequestMapping("/admin")
public class OrderListController {

    @Autowired
    OrderListService orderListService;

    // 创建订单
    @PostMapping("/orderlist")
    public Object addOrderList(@RequestBody OrderList orderList){
        return orderListService.addOrderList(orderList);
    }

    // 查询一页数据
    @GetMapping("/orderlist")
    public Object getOrderListsByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return orderListService.getOrderListsByRange(page, limit);
    }

    // 查询订单详细信息
    @GetMapping("/orderlist/{id}")
    public Object getOrderDetailsByOid(@PathVariable("id") String id){
        return orderListService.getOrderDetailsByOid(id);
    }

    // 修改订单状态
    @PutMapping("/orderlist/{id}/{status}")
    public Object updateOrderListStatus(@PathVariable("id") String id,@PathVariable("status") Integer status){
        return orderListService.updateOrderListStatus(id,status);
    }

}

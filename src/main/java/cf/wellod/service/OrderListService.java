package cf.wellod.service;

import cf.wellod.bean.Goods;
import cf.wellod.bean.OrderDetail;
import cf.wellod.bean.OrderList;
import cf.wellod.mapper.GoodsMapper;
import cf.wellod.mapper.OrderDetailMapper;
import cf.wellod.mapper.OrderListMapper;
import cf.wellod.mapper.StockMapper;
import cf.wellod.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderListService {

    @Autowired
    OrderListMapper orderListMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    StockMapper stockMapper;

    // 创建订单
    @Transactional
    public HashMap<String, Object> addOrderList(OrderList orderList){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            System.out.println(orderList);
            if(orderList.getAmount() == null || orderList.getDetailList().size() == 0 || orderList.getStatus() == null || orderList.getStatus() != 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                List<OrderDetail> list = orderList.getDetailList();
                Double amount = new Double(0.0);
                Goods goods;
                String oid = CommonUtil.generateOrderId();
                // 检查总金额一致
                for(OrderDetail orderDetail : list){
                    goods = goodsMapper.getGoodsById(orderDetail.getGid());
                    if(goods != null){
                        orderDetail.setOid(oid);
                        orderDetail.setDiscount(goods.getDiscount());
                        if(orderDetail.getQuantity() > goods.getShelfQuantity()){
                            retJson.put("code", -1);
                            retJson.put("msg", "invalid: over sell");
                            return retJson;
                        }

                        amount += orderDetail.getDiscount() * orderDetail.getQuantity() * goods.getBuyPrice();
                    }else{
                        retJson.put("code", -1);
                        retJson.put("msg", "invalid: unknown gid");
                        return retJson;
                    }
                }
                orderList.setId(oid);
                orderList.setAmount(amount);
                orderList.setCreateTime(new Date());

                // 其实此处还要检查amount是否一致和检查是否为过期商品

                orderListMapper.addOrderList(orderList); // 插入OrderList表
                for(OrderDetail orderDetail : list){
                    orderDetailMapper.addOrderDetail(orderDetail);  // 插入OrderDetail表
                }
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return retJson;
    }

    // 查询一页信息
    public HashMap<String,Object> getOrderListsByRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = orderListMapper.getOrderListsCount();
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
            List<OrderList> list = orderListMapper.getOrderListsByRange(start, limit);
            retJson.put("data", list);
            System.out.println(list);
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<OrderList>());
        }
        return retJson;
    }


    // 查看订单详细信息
    public HashMap<String,Object> getOrderDetailsByOid(String id){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(id == null){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                OrderList orderList = orderListMapper.getOrderListById(id);
                List<OrderDetail> list = orderDetailMapper.getOrderDetailsByOid(id);
                orderList.setDetailList(list);
                retJson.put("code", 0);
                retJson.put("msg", "success");
                retJson.put("count", list.size());
                retJson.put("data", orderList);
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<OrderList>());
        }
        return retJson;
    }

    // 修改订单状态
    @Transactional
    public HashMap<String,Object> updateOrderListStatus(String id, Integer status){
        HashMap<String,Object> retJson = new HashMap<>();
        try{
            if(status == null || status <= 0 || status > 2){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                orderListMapper.updateOrderListStatus(id,status,new Date());

                if(status == 1){
                    // 已支付
                    // 从货架减少商品数量并记账
                    OrderList orderList = orderListMapper.getOrderListById(id);
                    List<OrderDetail> list = orderDetailMapper.getOrderDetailsByOid(id);
                    if(orderList == null || list == null)
                    {
                        retJson.put("code", 0);
                        retJson.put("msg", "success");
                        return retJson;
                    }
                    for(OrderDetail orderDetail:list){
                        goodsMapper.minusGoodsShelfCount(orderDetail.getGid(), orderDetail.getQuantity());
                    }
                }
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }
}

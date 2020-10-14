package cf.wellod.mapper;

import cf.wellod.bean.OrderList;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderListMapper {
    // 插入一个订单
    public void addOrderList(OrderList orderList);

    // 查询订单总数
    public Integer getOrderListsCount();

    // 查询一页订单
    public List<OrderList> getOrderListsByRange(Integer start, Integer num);

    // 查询一个订单 By Id
    public OrderList getOrderListById(String id);

    // 修改订单状态
    public void updateOrderListStatus(String id, Integer status, Date payTime);
}

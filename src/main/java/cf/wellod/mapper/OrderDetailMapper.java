package cf.wellod.mapper;

import cf.wellod.bean.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    // 插入多条记录（一个订单的数据)
    public void addOrderDetailsOfOneOrder(List<OrderDetail> list);

    // 查询一个订单的订单明细
    public List<OrderDetail> getOrderDetailsByOid(String oid);

    // 插入一条记录
    public void addOrderDetail(OrderDetail orderDetail);

}

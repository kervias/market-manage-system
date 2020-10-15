package cf.wellod.mapper;

import cf.wellod.bean.MonthSale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonthSaleMapper {
    // 插入一条数据
    public void addMonthSale(MonthSale monthSale);

    // 得到一条数据
    public MonthSale getMonthSaleById(String id);

    // 得到一页数据
    public List<MonthSale> getMonthSalesByRange(Integer start, Integer num);

    // 获取数据数量
    public Integer getMonthSalesCount();

    // 判断是否存在
    public Integer isHaveMonthSaleById(String id);
}

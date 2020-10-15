package cf.wellod.mapper;

import cf.wellod.bean.YearSale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YearSaleMapper {

    // 插入一条数据
    public void addYearSale(YearSale yearSale);

    // 得到一条数据
    public YearSale getYearSaleById(String id);

    // 得到一页数据
    public List<YearSale> getYearSalesByRange(Integer start, Integer num);

    // 获取数据数量
    public Integer getYearSalesCount();

    // 判断是否存在
    public Integer isHaveYearSaleById(String id);

    // 更新一条数据
    public void updateYearSale(YearSale yearSale);
}

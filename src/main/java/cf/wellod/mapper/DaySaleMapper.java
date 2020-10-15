package cf.wellod.mapper;

import cf.wellod.bean.DaySale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DaySaleMapper {
    // 插入一条数据
    public void addDaySale(DaySale daySale);

    // 得到一条数据
    public DaySale getDaySaleById(String id);

    // 得到一页数据
    public List<DaySale> getDaySalesByRange(Integer start, Integer num);

    // 获取数据数量
    public Integer getDaySalesCount();

    // 判断是否存在
    public Integer isHaveDaySaleById(String id);

    // 更新一条数据
    public void updateDaySale(DaySale daySale);

}

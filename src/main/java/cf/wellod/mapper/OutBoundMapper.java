package cf.wellod.mapper;

import cf.wellod.bean.OutBound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutBoundMapper {
    // 插入出库记录
    public void addOutBoundRecord(OutBound outBound);

    // 查询某页数据
    public List<OutBound> getOutBoundsByRange(Integer start, Integer num);

    // 查询记录数量
    public Integer getOutBoundsCount();
}

package cf.wellod.mapper;

import cf.wellod.bean.InBound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InBoundMapper {
    // 添加入库数据
    public void addInBoundRecord(InBound inBound);

    // 查询某页数据
    public List<InBound> getInBoundsByRange(Integer start, Integer num);

    // 查询记录数量
    public Integer getInBoundsCount();
}

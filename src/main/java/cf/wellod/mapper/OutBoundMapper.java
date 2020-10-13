package cf.wellod.mapper;

import cf.wellod.bean.OutBound;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OutBoundMapper {
    // 插入出库记录
    public void addOutBoundRecord(OutBound outBound);
}

package cf.wellod.mapper;

import cf.wellod.bean.InBound;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InBoundMapper {
    // 添加入库数据
    public void addInBoundRecord(InBound inBound);
}

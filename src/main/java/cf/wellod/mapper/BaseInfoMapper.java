package cf.wellod.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseInfoMapper {

    // 获取版本信息
    public String getVersion();
}

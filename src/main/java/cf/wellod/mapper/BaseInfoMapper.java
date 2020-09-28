package cf.wellod.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoMapper {

    // 获取版本信息
    public String getVersion();
}

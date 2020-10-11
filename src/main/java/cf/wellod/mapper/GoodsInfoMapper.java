package cf.wellod.mapper;

import cf.wellod.bean.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsInfoMapper {
    // 添加一条记录
    public void addGoodsInfo(GoodsInfo goodsInfo);

    // 删除一条记录
    public void deleteGoodsInfoById(String EN13);

    // 删除多条记录
    public void deleteGoodsInfosBatch(List<String> delIdList);

    // 修改一条记录
    public void updateGoodsInfo(GoodsInfo goodsInfo);

    // 查询记录 By Range
    public List<GoodsInfo> getGoodsInfosByRange(Integer start, Integer num);

    // 查询所有记录
    public List<GoodsInfo> getGoodsInfosAll();

    // 查询记录数目
    public Integer getGoodsInfosCount();

    // 查询一条记录
    public GoodsInfo getGoodsInfoById(String EN13);
}

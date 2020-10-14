package cf.wellod.mapper;

import cf.wellod.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    //添加一条记录
    public void addGoods(Goods goods);

    //查询某个记录
    public Goods getGoodsById(String id);

    //删除某条记录
    public void deleteGoodsById(String id);

    //批量删除某些记录
    public void deleteGoodsesBatch(List<String> list);

    //修改某条记录
    public void updateGoods(Goods goods);

    //表中的记录数
    public Integer getGoodsesCount();

    //查询某页的记录
    public List<Goods> getGoodsesByRange(Integer start,Integer num);

    //查询所有记录
    public List<Goods> getGoodsesAll();

    // 添加商品货架数量
    public void addGoodsShelfCount(String id, Integer num);

    // 销售时减少货架数量
    public void minusGoodsShelfCount(String id, Integer num);

    // 人为修改货架数量
    public void updateGoodsShelfCount(String id, Integer num);
}

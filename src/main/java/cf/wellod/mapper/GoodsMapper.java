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
}

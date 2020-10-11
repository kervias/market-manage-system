package cf.wellod.mapper;

import cf.wellod.bean.WareHouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WareHouseMapper {

    // 获取表中的数据项个数
    public Integer getWareHousesCount();

    //获取一页的数据
    public List<WareHouse> getWareHousesByRange(Integer start,Integer num);

    //插入一条记录
    public void addWareHouse(WareHouse wareHouse);

    //修改一条记录
    public void updateWareHouse(WareHouse wareHouse);

    //删除一条记录
    public void deleteWareHouseById(Integer id);

    //批量删除一些记录
    public void deleteWareHouseBatch(List<Integer> list);

    //查看所有记录
    public List<WareHouse> getWareHousesAll();
}

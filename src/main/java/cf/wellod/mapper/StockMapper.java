package cf.wellod.mapper;

import cf.wellod.bean.InBound;
import cf.wellod.bean.OutBound;
import cf.wellod.bean.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {

    // 获取当前数据库中是否包含某个记录
    public Integer isExistsRecord(String gid, Integer wid);

    // 入库操作，增加库存量
    public void inBoundStock(InBound inBound);

    // 出库操作，减少库存量
    public void outBoundStock(OutBound outBound);

    // 新记录入库操作
    public void addInBoundStock(InBound inBound);

    // 根据主键获取一条库存记录
    public Stock getStockByPrimaryKey(String gid, Integer wid);

    // 获取所有库存记录
    public List<Stock> getStockAll();

    // 获取某一页库存记录
    public List<Stock> getStockByRange(Integer start, Integer num);

    // 删除某条库存记录
    public void deleteStockByPrimaryKey(String gid, Integer wid);

    // 修改阈值
    public void updateThreshold(Stock stock);

    // 获取所有记录数量
    public Integer getStockCount();

}

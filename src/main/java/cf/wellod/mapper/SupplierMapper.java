package cf.wellod.mapper;

import cf.wellod.bean.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierMapper {

    // 获取一个供应商
    public Supplier getSuppById(Integer id);

    // 插入一个供应商
    public void insertSupp(Supplier supplier);

    // 获取表中的数据项数目
    public Integer getSuppCount();

    // 查询某个范围内的数据
    public List<Supplier> getEmpsByRange(Integer start, Integer num);

    // 删除某个Supplier
    public void deleteSuppById(Integer id);

    // 批量删除某些Supplier
    public void deleteSupps(List<Integer> delIdList);

    // 更新某个Supplier信息
    public void updateSupp(Supplier supplier);

    //获取表中所有记录
    public List<Supplier> getSuppsAll();
}

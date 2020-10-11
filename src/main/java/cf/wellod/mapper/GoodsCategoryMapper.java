package cf.wellod.mapper;

import cf.wellod.bean.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsCategoryMapper {

    // 增加一个Category
    public void addCategory(Category category);

    // 删除一个Category ById
    public void deleteCategoryById(Integer id);

    // 批量删除Category
    public void deleteCategoriesBatch(List<Integer> delIdList);

    // 修改某个Category
    public void updateCategory(Category category);

    // 查询Categories ByRange
    public List<Category> getCategoriesByRange(Integer start, Integer num);

    // 查询Categories个数
    public Integer getCategoriesCount();

    // 查询所有Categories
    public List<Category> getCategoriesAll();
}

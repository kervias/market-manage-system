package cf.wellod.controller;

import cf.wellod.bean.Category;
import cf.wellod.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class GoodsCategoryController {

    @Autowired
    GoodsCategoryService goodsCategoryService;
    // 添加一个Category
    @PostMapping("/category")
    public Object addCategory(@RequestBody Category category){
        return goodsCategoryService.addCategory(category);
    }

    // 删除一个Category
    @DeleteMapping("/category/{id}")
    public Object deleteCategoryById(@PathVariable(value = "id")Integer id){
        return goodsCategoryService.deleteCategory(id);
    }

    // 批量删除某些商品种类
    @DeleteMapping("/categories")
    public Object deleteCategoriesBatch(@RequestBody List<Integer> list){
        return goodsCategoryService.deleteCategoriesBatch(list);
    }

    // 修改某个Category信息
    @PutMapping("/category")
    public Object updateCategory(@RequestBody Category category){
        return goodsCategoryService.updateCategory(category);
    }

    // 查询某页Categories
    @GetMapping("/categories_range")
    public Object getCategoriesByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return goodsCategoryService.getCategoriesByRange(page, limit);
    }

    // 查询所有Categories
    @GetMapping("/categories_all")
    public Object getCategoriesAll(){
        return goodsCategoryService.getCategoriesAll();
    }

/*    // 查询某个Category
    public Object getCategoryById(){
        return null;
}*/

}

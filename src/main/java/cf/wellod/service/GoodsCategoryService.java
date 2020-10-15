package cf.wellod.service;

import cf.wellod.bean.Category;
import cf.wellod.mapper.GoodsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsCategoryService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    public HashMap<String, Object> addCategory(Category category) {
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(category.getName() == null){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                goodsCategoryMapper.addCategory(category);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String,Object> deleteCategory(Integer id){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            goodsCategoryMapper.deleteCategoryById(id);
            retJson.put("code", 0);
            retJson.put("msg", "success");
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String,Object> deleteCategoriesBatch(List<Integer> list){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(list.size() == 0){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                goodsCategoryMapper.deleteCategoriesBatch(list);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String, Object> updateCategory(Category category){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            if(category.getName() == null){
                retJson.put("code", -1);
                retJson.put("msg", "invalid");
            }else{
                goodsCategoryMapper.updateCategory(category);
                retJson.put("code", 0);
                retJson.put("msg", "success");
            }
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }

    public HashMap<String, Object> getCategoriesByRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        Integer count = goodsCategoryMapper.getCategoriesCount();
        if(count >= 0 && limit > 0 && page > 0)
        {
            int pageNum = (int)Math.ceil(count.floatValue()/limit);
            if(pageNum == 0) pageNum = 1;
            if(page > pageNum)page = pageNum;
            int start = (page-1)*limit+1;
            System.out.println("start "+start);
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("count", count);
            List<Category> list = goodsCategoryMapper.getCategoriesByRange(start,limit);
            retJson.put("data", list);
            System.out.println(list);
        }else{
            retJson.put("code", 0);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<Category>());
        }
        return retJson;
    }

    public HashMap<String, Object> getCategoriesAll(){
        HashMap<String,Object> retJson = new HashMap<String,Object>();
        try{
            List<Category> list = goodsCategoryMapper.getCategoriesAll();
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("data",list);
        }catch (Exception e){
            retJson.put("code", -1);
            retJson.put("msg", "failed");
        }
        return retJson;
    }
}

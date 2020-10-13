package cf.wellod.service;

import cf.wellod.bean.InBound;
import cf.wellod.mapper.InBoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InBoundService {

    @Autowired
    InBoundMapper inBoundMapper;

    public HashMap<String, Object> getInBoundsByRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = inBoundMapper.getInBoundsCount();
        if(count >= 0 && limit > 0 && page > 0)
        {
            int pageNum = (int)Math.ceil(count.floatValue()/limit);
            if(pageNum == 0) pageNum++;
            if(page > pageNum) page = pageNum;
            int start = (page-1)*limit+1;
            System.out.println("start "+start);
            retJson.put("code", 0);
            retJson.put("msg", "success");
            retJson.put("count", count);
            List<InBound> list = inBoundMapper.getInBoundsByRange(start,limit);
            retJson.put("data", list);
            System.out.println(list);
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<InBound>());
        }

        return retJson;
    }

}

package cf.wellod.service;

import cf.wellod.bean.OutBound;
import cf.wellod.mapper.OutBoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OutBoundService {

    @Autowired
    OutBoundMapper outBoundMapper;

    public HashMap<String, Object> getInBoundsByRange(Integer page, Integer limit){
        HashMap<String,Object> retJson = new HashMap<>();
        Integer count = outBoundMapper.getOutBoundsCount();
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
            List<OutBound> list = outBoundMapper.getOutBoundsByRange(start,limit);
            retJson.put("data", list);
            System.out.println(list);
        }else{
            retJson.put("code", -1);
            retJson.put("msg", "failed");
            retJson.put("count", 0);
            retJson.put("data", new ArrayList<OutBound>());
        }
        return retJson;
    }
}

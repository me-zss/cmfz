package com.shun.service;

import com.shun.dao.CounterDao;
import com.shun.entity.Counter;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterDao counterDao;
    @Override
    public Map add(Counter counter) {
        Map map = new HashMap();
        counter.setId(UUID.randomUUID().toString());
        int insert = counterDao.insertSelective(counter);
        Map counterMap = findByCourseId(counter.getTaskId());
        map.put("status",200);
        map.put("counters",counterMap.get("counters"));
        return map;
    }

    @Override
    public Map findByCourseId(String id) {
        Map map = new HashMap();
        Example example = new Example(Counter.class);
        example.createCriteria().andEqualTo("taskId",id);
        List<Counter> counters = counterDao.selectByExample(example);
        map.put("status", 200);
        map.put("counters",counters);
        return map;
    }

    @Override
    public Map del(String id) {
        Counter counter = counterDao.selectByPrimaryKey(new Counter().setId(id));
        int i = counterDao.deleteByPrimaryKey(new Counter().setId(id));
        Map counterMap = findByCourseId(counter.getTaskId());
        Map map = new HashMap();
        if (i == 0) {
            map.put("status", -200);
        } else {
            map.put("status",200);
        }
        map.put("counters",counterMap.get("counters"));
        return map;
    }

    @Override
    public Map update(Counter setCount) {
        Counter counter = counterDao.selectByPrimaryKey(new Counter().setId(setCount.getId()));
        int i = counterDao.updateByPrimaryKeySelective(setCount);
        Map counterMap = findByCourseId(counter.getTaskId());
        Map map = new HashMap();
        if (i == 0) {
            map.put("status", -200);
        } else {
            map.put("status",200);
        }
        map.put("counters",counterMap.get("counters"));
        return map;
    }
}

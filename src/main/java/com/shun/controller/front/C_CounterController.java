package com.shun.controller.front;

import com.shun.entity.Counter;
import com.shun.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("counterPage")
public class C_CounterController {
    @Autowired
    private CounterService counterService;

    @RequestMapping("list")
    public Map list(String uid,String id) {
        return counterService.findByCourseId(id);
    }
    @RequestMapping("add")
    public Map add(String uid,String title,String id) {
        return counterService.add(new Counter().setUserId(uid).setTaskId(id).setName(title));
    }
    @RequestMapping("del")
    public Map del(String uid,String id) {
        return counterService.del(id);
    }
    @RequestMapping("updateCount")
    public Map updateCount(String uid,String id,Integer count) {
        return counterService.update(new Counter().setId(id).setCount(count));
    }
}

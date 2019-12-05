package com.shun.controller.front;

import com.shun.entity.Guru;
import com.shun.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("guruPage")
public class C_GuruController {
    @Autowired
    private GuruService guruService;
    @RequestMapping("list")
    public Map list(String uid) {
        List<Guru> gurus = guruService.findAll();
        Map map = new HashMap();
        map.put("status",200);
        map.put("message","成功");
        map.put("list",gurus);
        return map;
    }
    @RequestMapping("addFocus")
    public Map addFocus(String uid,String id) {
        return guruService.addFocus(uid,id);
    }
}

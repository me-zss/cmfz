package com.shun.controller.front;

import com.shun.entity.Course;
import com.shun.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("coursePage")
public class C_CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("list")
    public Map listAll(String uid) {
        return courseService.findByUserId(uid);

    }
    @RequestMapping("add")
    public Map add(String uid,String title) {
        return courseService.add(new Course().setUserId(uid).setName(title));
    }
    @RequestMapping("del")
    public Map del(String uid,String id) {
        return courseService.del(id);
    }
}

package com.shun.service;

import com.shun.dao.CourseDao;
import com.shun.entity.Course;
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
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Override
    public Map findByUserId(String uid) {
        Example example = new Example(Course.class);
        example.createCriteria().andEqualTo("userId",uid);
        List<Course> courses = courseDao.selectByExample(example);
        Map map = new HashMap();
        if(courses==null){
            map.put("status",-200);
            map.put("message","未查询到数据");
        }else{
            map.put("status",200);
            map.put("courses",courses);
        }
        return map;
    }

    @Override
    public Map add(Course course) {
        Map map = new HashMap();
        course.setId(UUID.randomUUID().toString());
        int i = courseDao.insertSelective(course);
        Map userMap = findByUserId(course.getUserId());
        if(i==0){
            map.put("status",-200);
            map.put("message","添加失败");
        }else{
            map.put("status",200);
            map.put("option",userMap.get("courses"));
        }
        return map;
    }

    @Override
    public Map del(String id) {
        Map map = new HashMap();
        Course course = new Course().setId(id);
        Course reCourse = courseDao.selectOne(course);
        int i = courseDao.deleteByPrimaryKey(course);
        Map userMap = findByUserId(course.getUserId());
        map.put("status",200);
        map.put("option",userMap.get("courses"));
        return map;
    }
}

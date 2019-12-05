package com.shun.service;

import com.shun.entity.Course;

import java.util.Map;

public interface CourseService {

    Map findByUserId(String uid);

    Map add(Course course);

    Map del(String id);
}

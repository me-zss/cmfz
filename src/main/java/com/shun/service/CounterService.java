package com.shun.service;

import com.shun.entity.Counter;

import java.util.Map;

public interface CounterService {
    Map add(Counter counter);

    Map findByCourseId(String id);

    Map del(String id);

    Map update(Counter setCount);
}

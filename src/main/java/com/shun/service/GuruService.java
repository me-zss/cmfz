package com.shun.service;

import com.shun.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    List<Guru> findAll();
    Guru findById(String id);

    Map addFocus(String uid, String id);
}

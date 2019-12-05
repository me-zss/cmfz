package com.shun.service;

import com.shun.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map findByMusicId(String musicId,Integer page,Integer rows);
    Map add(Chapter chapter);
    Map update(Chapter chapter);
    Map del(String[] ids);
    String getMusicId(String id);
    void delByMusicIds(String[] id);
}

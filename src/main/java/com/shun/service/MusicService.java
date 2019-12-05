package com.shun.service;

import com.shun.entity.Music;

import java.util.List;
import java.util.Map;

public interface MusicService {
    Map findAll(Integer page,Integer rows);
    Map add(Music music);
    Map update(Music music);
    Map del(String[] ids);
    Map addEpisodes(String musicId,Integer size);

    Map findById(String id);

    List<Music> findByRandom(int i);
}

package com.shun.service;

import com.shun.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Map add(Article article);
    Map findAll(Integer page,Integer rows);
    Map del(String[] ids);
    Map update(Article article);
    Map findByWords(Article article,Integer page,Integer rows);
    List<Article> findByGuruId(String[] asList);

    Map findById(String id);

    List<Article> findByRandom(int i);
}

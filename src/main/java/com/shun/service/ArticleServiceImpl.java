package com.shun.service;

import com.shun.annotation.LogAnnotation;
import com.shun.dao.ArticleDao;
import com.shun.entity.Article;
import com.shun.entity.Music;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    @LogAnnotation(value = "添加文章")
    public Map add(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreateTime(new Date());
        article.setReleaseTime(new Date());
        articleDao.insert(article);
        Map map = new HashMap();
        map.put("status",200);
        return map;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findAll(Integer page, Integer rows) {
        Map map = new HashMap();
        int start = (page-1) * rows;
        List<Article> articles = articleDao.selectByRowBounds(new Article(), new RowBounds(start, rows));
        int records = articleDao.selectCount(new Article());
        int total = records % rows==0?records/rows:records/rows+1;
        map.put("rows",articles);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }
    @Override
    @LogAnnotation(value = "删除文章")
    public Map del(String[] ids) {
        articleDao.deleteByIdList(Arrays.asList(ids));
        Map map = new HashMap();
        map.put("status",200);
        return map;
    }
    @Override
    @LogAnnotation(value = "更新轮播图")
    public Map update(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
        Map map = new HashMap();
        map.put("status",200);
        return map;
    }

    @Override
    public Map findByWords(Article article,Integer page,Integer rows) {
        Map map = new HashMap();
        Example e = new Example(Article.class);
        int start = (page-1) * rows;
        e.createCriteria().andLike("contents",article.getContents());
        List<Article> articles = articleDao.selectByExampleAndRowBounds(e,new RowBounds(start,rows));
        int records = articleDao.selectCountByExample(e);
        int total = records % rows==0?records/rows:records/rows+1;
        map.put("status",200);
        map.put("rows",articles);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);

        return null;
    }

    @Override
    public List<Article> findByGuruId(String[] list) {
        Example example = new Example(Article.class);
        example.createCriteria().andIn("id",Arrays.asList(list));
        return articleDao.selectByExample(example);
    }

    @Override
    public Map findById(String id) {
        Map map = new HashMap();
        Article article = articleDao.selectByPrimaryKey(new Article().setId(id));
        if(article==null){
            map.put("status",-200);
            map.put("message","未查询到文章");
        }else{
            map.put("status",200);
            map.put("article",article);
        }
        return map;
    }

    @Override
    public List<Article> findByRandom(int i) {
        Article article = new Article();
        article.setStatus("0");
        int count = articleDao.selectCount(article);
        Random random = new Random();
        int start = random.nextInt(count);
        return articleDao.selectByRowBounds(article, new RowBounds(start, i));
    }
}

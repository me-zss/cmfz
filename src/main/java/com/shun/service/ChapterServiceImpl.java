package com.shun.service;

import com.shun.annotation.LogAnnotation;
import com.shun.dao.ChapterDao;
import com.shun.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findByMusicId(String musicId,Integer page,Integer rows) {
        int start =(page-1)*rows;
        Example e = new Example(Chapter.class);
        Map map = new HashMap();
        e.createCriteria().andEqualTo("musicId", musicId);
        List<Chapter> cs = chapterDao.selectByExampleAndRowBounds(e, new RowBounds(start, rows));
        int records = chapterDao.selectCountByExample(e);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",cs);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    @LogAnnotation(value = "添加章节")
    public Map add(Chapter chapter) {
        Map map = new HashMap();
        String id = UUID.randomUUID().toString();
        chapter.setId(id);
        chapter.setCreateDate(new Date());
        chapterDao.insertSelective(chapter);
        map.put("chapterId",id);
        map.put("status", 200);
        return map;
    }

    @Override
    @LogAnnotation(value = "更新章节")
    public Map update(Chapter chapter) {
        Map map = new HashMap();
        chapterDao.updateByPrimaryKeySelective(chapter);
        map.put("status", 200);
        return map;
    }

    @Override
    @LogAnnotation(value = "删除章节")
    public Map del(String[] ids) {
        Map map = new HashMap();
        chapterDao.deleteByIdList(Arrays.asList(ids));
        map.put("status", 200);
        return map;
    }

    @Override
    public String getMusicId(String id) {
        Chapter chapter = chapterDao.selectOne(new Chapter().setId(id));
        return chapter.getMusicId();
    }

    @Override
    @LogAnnotation(value = "清除专辑内所有章节")
    public void delByMusicIds(String[] id) {
        Example e = new Example(Chapter.class);
        e.createCriteria().andIn("musicId",Arrays.asList(id));
        chapterDao.deleteByExample(e);
    }
}

package com.shun.service;

import com.shun.annotation.LogAnnotation;
import com.shun.dao.MusicDao;
import com.shun.entity.Banner;
import com.shun.entity.Music;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicDao musicDao;
    @Autowired
    private ChapterService chapterService;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findAll(Integer page, Integer rows) {
        int start = (page-1)*rows;
        Map map = new HashMap();
        List<Music> music = musicDao.selectByRowBounds(new Music(), new RowBounds(start, rows));
        int records = musicDao.selectCount(new Music());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",music);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    @LogAnnotation(value = "添加专辑")
    public Map add(Music music) {
        Map map = new HashMap();
        String musicId = UUID.randomUUID().toString();
        music.setId(musicId);
        music.setCreateTime(new Date());
        music.setPic(null);
        music.setEpisodes(0);
        musicDao.insert(music);
        map.put("status",200);
        map.put("musicId",musicId);
        return map;
    }

    @Override
    @LogAnnotation(value = "更新专辑")
    public Map update(Music music) {
        Map map = new HashMap();
        musicDao.updateByPrimaryKeySelective(music);
        map.put("musicId",music.getId());
        map.put("status",200);
        return map;
    }

    @Override
    @LogAnnotation(value = "删除章节")
    public Map del(String[] ids) {
        Map map = new HashMap();
        musicDao.deleteByIdList(Arrays.asList(ids));
        chapterService.delByMusicIds(ids);
        map.put("status",200);
        return map;
    }

    @Override
    public Map addEpisodes(String musicId, Integer size) {
        Map map = new HashMap();
        Music music = musicDao.selectOne(new Music().setId(musicId));
        int m = music.getEpisodes()+size;
        m = m>=0?m:0;
        musicDao.updateByPrimaryKeySelective(new Music().setId(music.getId()).setEpisodes(m));
        map.put("status",200);
        return map;
    }

    @Override
    public Map findById(String id) {
        Map map = new HashMap();
        Music music = musicDao.selectByPrimaryKey(new Music().setId(id));
        if(music==null){
            map.put("status",-200);
            map.put("message","未查询到数据");
        }else{
            map.put("status",200);
            map.put("music",music);
        }
        return map;
    }

    @Override
    public List<Music> findByRandom(int i) {
        Music music = new Music();
        music.setStatus("1");
        int count = musicDao.selectCount(music);
        Random random = new Random();
        int start = random.nextInt(count);
        return musicDao.selectByRowBounds(music, new RowBounds(start, i));
    }
}

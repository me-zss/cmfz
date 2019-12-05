package com.shun.service;

import com.shun.dao.GuruDao;
import com.shun.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> findAll() {
        return  guruDao.selectAll();
    }

    @Override
    public Guru findById(String id) {
        return guruDao.selectOne(new Guru().setId(id));
    }

    @Override
    public Map addFocus(String uid, String id) {
        Map map = new HashMap();
        try {
            SetOperations<String, String> sset = stringRedisTemplate.opsForSet();
            sset.add(uid, id);
            sset.add(id, uid);
            Set<String> members = sset.members(uid);
            String[] gurusIds = members.toArray(new String[members.size()]);
            Example example = new Example(Guru.class);
            example.createCriteria().andIn("id", Arrays.asList(gurusIds));
            List<Guru> gurus = guruDao.selectByExample(example);
            map.put("status", 200);
            map.put("list",gurus);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",-200);
            map.put("message","关注失败");
        }
        return map;
    }
}

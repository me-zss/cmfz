package com.shun.controller.front;

import com.shun.entity.Article;
import com.shun.entity.Banner;
import com.shun.entity.Music;
import com.shun.service.ArticleService;
import com.shun.service.BannerService;
import com.shun.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("firstPage")
public class C_FirstPageController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private MusicService musicService;
    @Autowired
    private ArticleService articleService;
    @RequestMapping("getFirst")
    public Map getAll(String uid,String type,String sub_type) {
        Map map = new HashMap();
        map.put("status",200);
        if("all".equals(type)){
            List<Banner> banners = bannerService.findByRandom(5);
            List<Music> musics = musicService.findByRandom(6);
            List<Article> articles = articleService.findByRandom(6);
            map.put("head",banners);
            map.put("articles",articles);
            map.put("musics",musics);
        }else if("wen".equals(type)){
            Map musics = musicService.findAll(1, 20);
            map.put("musics",musics.get("rows"));
        }else if ("si".equals(type)){
            if("ssyj".equals(sub_type)){
                SetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForSet();
                Set<String> gurus = stringStringSetOperations.members(uid);
                List<Article> article = gurus.size()==0?null:articleService.findByGuruId(gurus.toArray(new String[gurus.size()]));
                map.put("articles",article);
            }else if("xmfy".equals(sub_type)){
                Map articles = articleService.findAll(1, 20);
                map.put("article",articles.get("rows"));
            }else{
                map.put("message","参数错误");
                map.put("status",-200);
            }
        }else{
            map.put("status",-200);
            map.put("message","参数错误");
        }
        return map;
    }
}

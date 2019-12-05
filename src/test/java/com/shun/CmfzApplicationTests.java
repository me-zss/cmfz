package com.shun;


import com.shun.dao.*;
import com.shun.entity.*;
import com.shun.service.BannerService;
import com.shun.service.UserService;
import com.shun.util.BannerPOIUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {
	/*@Autowired
	private UserDao userDao;
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private MusicDao musicDao;
	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private BannerDao bannerDao;
	@Autowired
	private BannerService bannerService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Test
	public void contextLoads() {
		List<User> users = userDao.selectAll();
		users.forEach(user-> System.out.println(user));
	}
	@Test
	public void testSelectOne() {
		Manager shun = managerDao.selectOne(new Manager().setPassword("123456"));
		System.out.println(shun);
	}
	@Test
	public void testRedis() {
		HashOperations<String, Object, Object> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
		stringRedisTemplate.expire("user",10000, TimeUnit.MINUTES);
	}
	@Test
	public void testBanner() {
		Map banners = bannerService.findAll(1, 5);
		List<Banner> list= (List<Banner>) banners.get("rows");
		list.forEach(b-> System.out.println(b));

	}
	@Test
	public void testMusic() {
		*//*List<Music> ms = musicDao.selectAll();
		ms.forEach(m-> System.out.println(m));*//*
		Example e = new Example(Music.class);
		e.createCriteria().andEqualTo("name","小强");
		List<Music> music = musicDao.selectByExample(e);
		music.forEach(m-> System.out.println(m));
	}
	@Test
	public void testChapter() {
		*//*List<Chapter> cs = chapterDao.selectAll();
		cs.forEach(c-> System.out.println(c));*//*
		Example e = new Example(Chapter.class);
		e.createCriteria().andEqualTo("musicId", "1");
		List<Chapter> cs = chapterDao.selectByExample(e);
		cs.forEach(c-> System.out.println(c));
	}
	@Test
	public void testH() {
		List<Banner> banners = bannerDao.selectAll();
		Workbook workbook = BannerPOIUtil.tranferToExcel(banners);
		try {
			workbook.write(new FileOutputStream(new File("/home/shun","bannerInfo.xls")));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

	@Autowired
	private UserService userService;
	@Test
	public void testUserService() {
		Map newUsers = userService.findNewUsers();
		Integer i = (Integer) newUsers.get("countTodayMan");
		Integer j = (Integer) newUsers.get("countWeekMan");
		Integer k = (Integer) newUsers.get("countMonthMan");
		Integer l = (Integer) newUsers.get("countYearMan");
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(l);
		Integer f1 = (Integer) newUsers.get("countTodayFeman");
		Integer f2 = (Integer) newUsers.get("countWeekFeman");
		Integer f3 = (Integer) newUsers.get("countMonthFeman");
		Integer f4 = (Integer) newUsers.get("countYearFeman");
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		System.out.println(f4);
		List<User> users = (List<User>) newUsers.get("users");
		users.forEach(user -> System.out.println(user));

	}

}

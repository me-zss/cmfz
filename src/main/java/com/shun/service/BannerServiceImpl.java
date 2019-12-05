package com.shun.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.shun.annotation.LogAnnotation;
import com.shun.dao.BannerDao;
import com.shun.entity.Banner;
import com.shun.poiListener.BannerListener;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerListener bannerListener;
    @Autowired
    private BannerDao bannerDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findAll(Integer page, Integer rows) {
        Map map = new HashMap();
        int start = (page-1) * rows;
        List<Banner> banners = bannerDao.selectByRowBounds(new Banner(), new RowBounds(start, rows));
        int records = bannerDao.selectCount(new Banner());
        int total = records % rows==0?records/rows:records/rows+1;
        map.put("rows",banners);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    @LogAnnotation(value = "添加轮播图")
    public Map add(Banner banner) {
        String bannerId = UUID.randomUUID().toString();
        Map map = new HashMap();
        banner.setId(bannerId);
        banner.setCreateDate(new Date());
        bannerDao.insert(banner);
        map.put("bannerId",bannerId);
        map.put("status",200);
        return map;
    }


    @Override
    @LogAnnotation(value = "更新轮播图")
    public Map update(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
        Map map = new HashMap();
        map.put("status",200);
        return map;
    }

    @Override
    @LogAnnotation(value = "删除轮播图")
    public Map del(String[] ids) {
        bannerDao.deleteByIdList(Arrays.asList(ids));
        Map map = new HashMap();
        map.put("status",200);
        return map;
    }

    @Override
    public List<Banner> findAll() {
        return bannerDao.selectAll();
    }

    @Override
    public void getExcel(HttpServletResponse response) {
        ExcelWriter build = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            build = EasyExcel.write(os,Banner.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("轮播图").build();
            Map map = null;
            int page = 1;
            do{
                map = findAll(page, 2000);
                Integer total = (Integer) map.get("total");
                page = total>page?page+1:0;
                List<Banner> banners = (List<Banner>) map.get("rows");
                build.write(banners,writeSheet);
            }while (page!=0);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            build.finish();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map importExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),Banner.class,bannerListener).doReadAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("status",200);
        return map;
    }

    @Override
    public void getEXCELModal(HttpServletResponse response){
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            Banner banner = new Banner(UUID.randomUUID().toString(), "美景", "http://xxxx", "轮播图", "article/getXXX", "1", new Date());
            EasyExcel.write(os,Banner.class).sheet().doWrite(Arrays.asList(banner));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List findByRandom(int i) {
        Banner banner = new Banner();
        banner.setStatus("1");
        int count = bannerDao.selectCount(banner);
        Random random = new Random();
        int start = random.nextInt(count);
        return bannerDao.selectByRowBounds(banner, new RowBounds(start, i));
    }
}

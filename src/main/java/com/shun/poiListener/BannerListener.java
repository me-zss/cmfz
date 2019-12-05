package com.shun.poiListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.shun.dao.BannerDao;
import com.shun.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BannerListener extends AnalysisEventListener<Banner> {
    @Autowired
    private BannerDao bannerDao;
    private List<Banner> list = new ArrayList<>();

    @Override
    public void invoke(Banner banner, AnalysisContext analysisContext) {
        banner.setId(UUID.randomUUID().toString());
        list.add(banner);
        if(list.size()>=2000){
            bannerDao.insertList(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if(list.size()>0){
            bannerDao.insertList(list);
            list.clear();
        }
    }
}

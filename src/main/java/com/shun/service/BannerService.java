package com.shun.service;

import com.shun.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BannerService {
    Map findAll(Integer page,Integer rows);
    Map add(Banner banner);
    Map update(Banner banner);
    Map del(String[] ids);
    List<Banner> findAll();
    void getExcel(HttpServletResponse response);
    void getExcel(File file);
    Map importExcel(MultipartFile file);

    void getExcelModal(HttpServletResponse response) throws IOException;

    List<Banner> findByRandom(int i);
}

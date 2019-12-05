package com.shun.controller;

import com.shun.entity.Banner;
import com.shun.service.BannerService;
import com.shun.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("findAll")
    public Map findAll(Integer page,Integer rows) {
        return bannerService.findAll(page, rows);
    }
    @RequestMapping("edit")
    public Map edit(String oper,Banner banner,String[] id) {
        Map map = null;
        if(oper.equals("add")){
            map = bannerService.add(banner);
        }else if(oper.equals("edit")){
            System.out.println(banner);
            banner.setUrl(null);
            map = bannerService.update(banner);
        }else if(oper.equals("del")){
            map = bannerService.del(id);
        }
        return map;
    }
    @RequestMapping("upload")
    public Map upload(MultipartFile url, String bannerId, HttpServletRequest request) throws IOException {
        String uri = null;
        try {
            uri = UploadUtil.getUrl("/img/banner", url, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        Banner b = new Banner();
        b.setId(bannerId);
        b.setUrl(uri);
        bannerService.update(b);
        map.put("status","ok");
        return map;
    }
    @RequestMapping("uploadXls")
    public Map uploadXls(MultipartFile fileInput ) {
        return bannerService.importExcel(fileInput);
    }
    @RequestMapping("getEXCEL")
    public void getEXCEL(HttpServletResponse response) throws UnsupportedEncodingException {
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("bannerInfo.xls","UTF-8"));
        bannerService.getExcel(response);
    }
    @RequestMapping("getEXCELModal")
    public void getEXCELModal(HttpServletResponse response) throws IOException {
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("bannerModel.xls","UTF-8"));
        bannerService.getEXCELModal(response);
    }
}

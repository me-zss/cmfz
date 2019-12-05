package com.shun.controller;

import com.shun.entity.Article;
import com.shun.entity.Guru;
import com.shun.service.ArticleService;
import com.shun.service.GuruService;
import com.shun.util.UploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private GuruService guruService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("edit")
    public Map edit(String oper,String[] id) {
        Map map = null;
        if ("del".equals(oper)) {
            map =  articleService.del(id);
        }
        return map;
    }
    @RequestMapping("upload")
    public Map upload(MultipartFile imgFile, HttpServletRequest request) {
        Map map = new HashMap();
        try {
            String url = UploadUtil.getUrl("/img/article", imgFile, request);
            map.put("error", 0);
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("url", "上传失败！");
        }
        return map;
    }
    @RequestMapping("addOrUpdate")
    public Map add(Article article) {
        if(!"0".equals(article.getUserId())){
            Guru guru = guruService.findById(article.getUserId());
            article.setAuthor(guru.getFname());
        }else{
            article.setAuthor("");
        }
        if("".equals(article.getId())){
            article.setId(null);
            return articleService.add(article);
        }else{
            return articleService.update(article);
        }
    }
    @RequestMapping("findByContents")
    public Map findByContents(Article article,Integer page,Integer rows) {
        return articleService.findByWords(article,page,rows);
    }
    @RequestMapping("findAll")
    public Map findAll(Integer rows,Integer page) {
        return articleService.findAll(page,rows);
    }

    @RequestMapping("findAllImage")
    public Map findAllImage(HttpServletRequest request) {
        Map map = new HashMap();
        map.put("current_url",request.getServletContext().getContextPath()+"/img/article/");
        List list = new ArrayList();
        String realPath = request.getServletContext().getRealPath("/img/article");
        File file = new File(realPath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            Map fileMap = new HashMap();
            fileMap.put("is_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            fileMap.put("filetype", FilenameUtils.getExtension(file1.getName()));
            fileMap.put("filename",file1.getName());
            String s = file1.getName().split("_")[0];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(s)));
            fileMap.put("datetime",format);
            list.add(fileMap);
        }
        map.put("file_list",list);
        map.put("total_count",list.size());
        return map;
    }

}

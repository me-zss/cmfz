package com.shun.controller;

import com.shun.entity.Chapter;
import com.shun.service.ChapterService;
import com.shun.service.MusicService;
import com.shun.util.AudioUtil;
import com.shun.util.UploadUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private MusicService musicService;
    @RequestMapping("findByMusicId")
    public Map findByMusicId(String musicId,Integer page,Integer rows) {
        return chapterService.findByMusicId(musicId,page,rows);
    }
    @RequestMapping("edit")
    public Map edit(String oper, Chapter chapter,String[] id) {
        Map map = new HashMap();
        if ("add".equals(oper)) {
            map = chapterService.add(chapter);
            musicService.addEpisodes(chapter.getMusicId(),1);
        } else if ("edit".equals(oper)) {
            map = chapterService.update(new Chapter().setId(chapter.getId()).setName(chapter.getName()));
        }else{
            String musicId = chapterService.getMusicId(id[0]);
            map = chapterService.del(id);
            musicService.addEpisodes(musicId,0-id.length);
        }
        return map;
    }
    @RequestMapping("upload")
    public Map upload(MultipartFile fileName, String chapterId, HttpServletRequest request) throws IOException {
        /*if(fileName.getOriginalFilename().equals("")){
            return null;
        }
        String realPath = session.getServletContext().getRealPath("/music/chapter");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String name = new Date().getTime()+"_"+fileName.getOriginalFilename();
        File chapterFile = new File(file, name);
        fileName.transferTo(chapterFile);
        String http = request.getScheme();
        String ip = InetAddress.getLocalHost().toString().split("/")[1];
        int port = request.getServerPort();
        String contextPath = request.getServletContext().getContextPath();
        String uri = http+"://"+ip+":"+port+contextPath+"/music/chapter/"+name;*/
        String uri = null;
        try {
            uri = UploadUtil.getUrl("/music/chapter",fileName,request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Chapter chapter = new Chapter();
        String realPath = request.getSession().getServletContext().getRealPath("/music/chapter");
        String name = uri.substring(uri.lastIndexOf("/")+1);
        Float audioSec = AudioUtil.getMp3Duration(new File(realPath, name));
        String time = (int)(audioSec/60)+":"+(int)(audioSec%60);
        long size = fileName.getSize();
        System.out.println(size);
        double fileSize = (double)size/1024/1024;
        BigDecimal b = new BigDecimal(fileSize);
        fileSize = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        String fileSizeStr = fileSize>0?fileSize+"MB":size/1024+"KB";


        chapter.setUrl(uri).setFileName(name).setTime(time).setId(chapterId).setSize(fileSizeStr);
        chapterService.update(chapter);
        Map map = new HashMap();
        /*
        */
        map.put("status","ok");
        return map;
    }

    @RequestMapping("down")
    public void down(String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/music/chapter");
        File file = new File(realPath, fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        String newName = fileName.substring(fileName.indexOf("_")+1);
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(newName,"UTF-8").replace("+"," "));
        ServletOutputStream out = response.getOutputStream();
        IOUtils.copy(fileInputStream,out);
        IOUtils.closeQuietly(out);
        IOUtils.closeQuietly(fileInputStream);
    }
}

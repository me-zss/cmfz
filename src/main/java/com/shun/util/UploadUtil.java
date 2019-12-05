package com.shun.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class UploadUtil {
    public static String getUrl(String path, MultipartFile fileIn, HttpServletRequest request) throws IOException {
        String fileName = fileIn.getOriginalFilename();
        String realPath = request.getSession().getServletContext().getRealPath(path);
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String newName = System.currentTimeMillis() +"_"+ fileName;
        String http = request.getScheme();
        String ip = InetAddress.getLocalHost().toString().split("/")[1];
        int port = request.getServerPort();
        String contextPath = request.getServletContext().getContextPath();
        fileIn.transferTo(new File(realPath,newName));
        return http+"://"+ip+":"+port+contextPath+path+"/"+newName;
    }
}

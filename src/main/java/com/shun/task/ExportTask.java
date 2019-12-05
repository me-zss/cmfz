package com.shun.task;

import com.shun.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author shun
 */
@Component
public class ExportTask {
    @Autowired
    private HttpSession session;
    @Autowired
    private BannerService bannerService;

    @Scheduled(cron = "0 0 0 ? * 1 *")
    public void exportFile() {
        String realPath = session.getServletContext().getRealPath("/file/xls");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        bannerService.getExcel(file);

    }
}

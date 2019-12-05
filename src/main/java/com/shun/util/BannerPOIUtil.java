package com.shun.util;

import com.shun.entity.Banner;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class BannerPOIUtil {
    public static Workbook tranferToExcel(List<Banner> banners) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("轮播图信息");
        //标题
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("编号");
        titleRow.createCell(1).setCellValue("标题");
        titleRow.createCell(2).setCellValue("状态");
        titleRow.createCell(3).setCellValue("描述");
        titleRow.createCell(4).setCellValue("创建时间");
        titleRow.createCell(5).setCellValue("图片");

        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        cellStyle.setDataFormat(format);
        for (int i = 0; i < banners.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(banners.get(i).getId());
            row.createCell(1).setCellValue(banners.get(i).getName());
            row.createCell(2).setCellValue(banners.get(i).getStatus());
            row.createCell(3).setCellValue(banners.get(i).getDescription());
            Cell cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(banners.get(i).getCreateDate());
            row.createCell(5).setCellValue(banners.get(i).getUrl());
        }
       return workbook;
    }
}

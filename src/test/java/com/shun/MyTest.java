package com.shun;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MyTest {
    @Test
    public void testH() {
        System.out.println("byte:"+Byte.MAX_VALUE);
        System.out.println("short:"+Short.MAX_VALUE);
        System.out.println("Integer:"+Integer.MAX_VALUE);
    }
    @Test
    public void testPOI() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生管理");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("我是顺顺");
        workbook.write(new FileOutputStream(new File("/home/shun","test.xls")));
        workbook.close();

    }
    @Test
    public void testMath() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int k = 0; k < 20; k++) {
            Calendar calendar = Calendar.getInstance();
            Random random = new Random();
            int i = random.nextInt(4);
            int pow = (int)Math.pow(i, 4);
            calendar.add(Calendar.DAY_OF_YEAR,-pow);
            String format = simpleDateFormat.format(calendar.getTime());
            System.out.println(format);
        }
    }
    @Test
    public void testAdd() {
        System.out.println("jsdj");
        System.out.println("lskd");
    }
}

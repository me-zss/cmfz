package com.shun;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestEasyExcel {
    private String fileName;
    @Before
    public void setzFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        fileName = "/home/shun/test_"+format+".xls";
    }
    public List getList() {
        List<MyData> lists = new ArrayList<>();
        for(int i=0;i<500;i++){
            MyData d1 = new MyData("用户"+i, new Date(), 60000D);
            MyData d2 = new MyData("员工"+i, new Date(), 50000D);
            MyData d3 = new MyData("shun"+i, new Date(), 40000D);
            MyData d4 = new MyData("haha"+i, new Date(), 30000D);
            lists.addAll(Arrays.asList(d1, d2, d3, d4));
        }
        return lists;
    }
    @Test
    public void test01() {
            MyData d1 = new MyData("用户", new Date(), 60000D);
            MyData d2 = new MyData("员工", new Date(), 50000D);
            MyData d3 = new MyData("shun", new Date(), 40000D);
            MyData d4 = new MyData("haha", new Date(), 30000D);
            List<MyData> lists = Arrays.asList(d1, d2, d3, d4);
            EasyExcel.write(new File("/home/shun/test01_1.xls"),MyData.class).sheet("员工").doWrite(lists);
    }
    @Test
    public void test02() {
        ExcelWriter build = EasyExcel.write(new File(fileName), MyData.class).build();
        WriteSheet w = EasyExcel.writerSheet("学生管理").build();
        ExcelWriter write = build.write(getList(), w);
        build.finish();
    }
    @Test
    public void test03() {
        //EasyExcel.read(new File("/home/shun/test_2019-12-02_025352.xls"),MyData.class,new MyDateReaderListener()).sheet("学生管理").doRead();;
        ExcelReader build = EasyExcel.read(new File("/home/shun/test_2019-12-02_025352.xls"), MyData.class, new MyDateReaderListener()).build();
        ReadSheet re = EasyExcel.readSheet("学生管理").build();
        ExcelReader read = build.read(re);
        build.finish();
    }
    @Test
    public void test04() {
        Set set = new HashSet<String>();
        set.add("d");
        ExcelWriter build = EasyExcel.write(new File(fileName), MyData.class).excludeColumnFiledNames(set).build();
        WriteSheet wr = EasyExcel.writerSheet("员工管理").build();
        ExcelWriter write = build.write(getList(), wr);
        write.finish();
    }
    @Test
    public void test05() {
        ExcelWriter build = EasyExcel.write(new File(fileName), MyData.class).build();
        WriteSheet wr = EasyExcel.writerSheet("学生管理").build();
        for (int i=0;i<10;i++){
            build.write(getList(),wr);
        }
        build.finish();
    }
    @Test
    public void test06() {
        ExcelWriter build = EasyExcel.write(new File(fileName), MyData.class).build();
        for(int j=0;j<30;j++) {
            WriteSheet wr = EasyExcel.writerSheet(j,"学生管理"+j).build();
            for (int i = 0; i < 30; i++) {
                build.write(getList(), wr);
            }
        }
        build.finish();
    }
}

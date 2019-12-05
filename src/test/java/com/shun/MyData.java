package com.shun;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadRowHeight(20)
@ContentRowHeight(15)
@ColumnWidth(30)
public class MyData {
    @ExcelProperty("用户名")
    private String name;
    @ExcelProperty("生日")
    @DateTimeFormat("yyyy年MM月dd日")
    private Date date;
    @ExcelProperty("工资")
    private Double d;
}

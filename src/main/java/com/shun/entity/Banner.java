package com.shun.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    /** id */
    @ExcelProperty("id")
    @ColumnWidth(15)
    private String id ;
    /** 名称 */
    @ExcelProperty("名称")
    @ColumnWidth(20)
    private String name ;
    /** 图片路径 */
    @ExcelProperty("图片路径")
    @ColumnWidth(15)
    private String url ;
    /** 简介 */
    @ExcelProperty("简介")
    @ColumnWidth(30)
    private String description ;
    /** 链接 */
    @ExcelProperty("链接")
    @ColumnWidth(15)
    private String href ;
    /** 状态 */
    @ExcelProperty("状态")
    @ColumnWidth(10)
    private String status ;
    /** 创建时间 */
    @ExcelProperty("创建时间")
    @ColumnWidth(30)
    private Date createDate;
}
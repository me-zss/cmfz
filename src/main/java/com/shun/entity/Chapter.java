package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Chapter {
    @Id
    /** 章节id */
    private String id ;
    /** 专辑id */
    private String musicId ;
    /** 章节名称 */
    private String name ;
    /** 字节大小 */
    private String size ;
    /** 时长 */
    private String time ;
    /** 文件路径 */
    private String url ;
    /** 文件名*/
    private String fileName;
    /**  上传时间 */
    private Date createDate;
}

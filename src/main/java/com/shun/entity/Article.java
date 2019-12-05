package com.shun.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Article {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    /** 文章id */
    private String id ;
    /** 标题 */
    private String title ;
    /** 作者id */
    private String userId ;
    /** 作者 */
    private String author ;
    /** 封面 */
    private String pic ;
    /** 内容 */
    private String contents;
    /** 创建时间 */
    private Date createTime ;
    /** 发布时间 */
    private Date releaseTime;
    /** 状态 */
    private String status;
}

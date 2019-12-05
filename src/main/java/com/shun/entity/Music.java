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
public class Music {
    @Id
    /** id */
    private String id ;
    /** 标题 */
    private String name ;
    /** 作者id */
    private String author;
    /** 播音id */
    private String beam;
    /** 集数 */
    private Integer episodes ;
    /** 星级 */
    private Integer star ;
    /** 封面 */
    private String pic ;
    /** 简介 */
    private String description ;
    /** 内容 */
    private String content ;
    /** 创建时间 */
    private Date createTime ;
    /** 状态 */
    private String status;
}

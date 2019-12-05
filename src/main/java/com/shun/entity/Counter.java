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
public class Counter {
    /** 计数器id */
    @Id
    private String id ;
    /** 名称 */
    private String name ;
    /** 功课id */
    private String taskId ;
    /** 用户id */
    private String userId ;
    /** 创建时间 */
    private Date createdTime ;
    /** 计数 */
    private Integer count ;

}

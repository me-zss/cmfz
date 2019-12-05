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
public class Course {
    @Id
    /** 类型id */
    private String id ;
    /** 标题 */
    private String name ;
    /** 创建时间 */
    private Date createTime ;
    /** 创建人id */
    private String userId ;

}

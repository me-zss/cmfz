package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Guru {
    @Id
    /** 上师id */
    private String id ;
    /** 上师姓名 */
    private String name ;
    /** 法号 */
    private String fname ;
    /** 头像 */
    private String pic ;
}

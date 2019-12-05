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
public class Manager {
    @Id
    /** 管理员id */
    private String id ;
    /** 用户名 */
    private String username ;
    /** 密码 */
    private String password ;

}

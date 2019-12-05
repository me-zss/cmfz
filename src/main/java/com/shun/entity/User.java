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
public class User {
    @Id
    private String id ;
    /** 手机号 */
    private String telphone ;
    /** 密码 */
    private String password ;
    /** 盐;密码md5盐 */
    private String salt ;
    /** 状态 */
    private String status ;
    /** 姓名 */
    private String name ;
    /** 性别 */
    private String sex ;
    /** 法名 */
    private String fname ;
    /** 地址 */
    private String loc ;
    /** 签名 */
    private String signature ;
    /** 头像 */
    private String img ;
    /** 注册时间;注册时间 */
    private Date createTime ;
    /** 最后登录时间 */
    private Date lastOnlineTime ;

}

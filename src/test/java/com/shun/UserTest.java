package com.shun;

import com.shun.entity.User;
import com.shun.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserService userService;
    @Test
    public void testChageStatus() {
        for(int i=0;i<10;i++){
            userService.modify(new User().setId("1").setStatus("1"));
        }
    }
    @Test
    public void testSout() {
        System.out.println("lllll");
    }
    @Test
    public void testAddUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setSignature("lalallala")
                    .setLoc(getOneProvince())
                    .setStatus(getOneOfMore("1","2"))
                    .setSex(getOneOfMore("男","女"))
                    .setName(getName())
                    .setFname(getName())
                    .setCreateTime(getDate())
                    .setImg("http://127.0.1.1:1208/cmfz/img/a.jpg")
                    .setLastOnlineTime(new Date())
                    .setPassword(getPassword(6))
                    .setSalt(getPassword(12))
                    .setTelphone(getMobile());
            userService.addUser(user);
        }
    }
    //随机生成一个日期 当天 一周内 一月内 一年内
    public  Date getDate() {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        int i = random.nextInt(4);
        int pow = (int)Math.pow(i, 4);
        calendar.add(Calendar.DAY_OF_YEAR,-pow);
        return calendar.getTime();
    }
    //随机生成一个省份
    public  String getOneProvince() {
        String[] cities = { "北京","天津","上海","重庆","河北","河南","云南","辽宁","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西","甘肃","山西","陕西","吉林","福建","贵州","广东","青海","西藏","四川","宁夏","海南","台湾","香港","澳门","内蒙古","黑龙江","南海诸岛"};
        Random random = new Random();
        int i = random.nextInt(cities.length);
        return cities[i];
    }
    //随机生成n个字符串的其中一个
    public  String getOneOfMore(String ...o) {
        Random random = new Random();
        int i = random.nextInt(o.length);
        return o[i];
    }
    //随机生成一个密码
    public  String getPassword(Integer size) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    //随机生成一个邮箱
    public  String getEmail() {
        StringBuffer m = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<8;i++) {
            m.append(rd.nextInt(10));
        }
        m.append("@qq.com");
        return m.toString();
    }
    //随机生成一个电话
    public  String getMobile() {
        StringBuffer m = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<11;i++) {
            m.append(rd.nextInt(10));
        }
        return m.toString();
    }
    //随机生成一个姓名
    public  String getName() {
        String[] firstNameArray = { "李", "王", "张", "刘", "陈", "杨", "赵", "黄","钟", "周", "吴", "徐", "孙", "胡", "朱", "高", "欧阳",
                "太史", "端木", "上官", "司马" };// 20个姓，其中5个复姓
        String[] lastNameArray = { "伟", "勇", "军", "磊", "涛", "斌", "强", "鹏", "杰", "峰", "超", "波", "辉", "刚", "健", "明", "亮",
                "俊", "飞", "凯", "浩", "华", "平", "鑫", "毅", "林", "洋", "宇", "敏", "宁", "建", "兵", "旭", "雷", "锋", "彬", "龙", "翔",
                "阳", "剑", "静", "敏", "燕", "艳", "丽", "娟", "莉", "芳", "萍", "玲", "娜", "丹", "洁", "红", "颖", "琳", "霞", "婷", "顺","慧",
                "莹", "晶", "华", "倩", "英", "佳", "梅", "雪", "蕾", "琴", "璐", "伟", "云", "蓉", "青", "薇", "欣", "琼", "宁", "平",
                "媛" };// 80个常用于名字的单字
        Random rd = new Random();
        int firstPos = rd.nextInt(21);
        StringBuilder name = new StringBuilder(firstNameArray[firstPos]);
        int lastLen = rd.nextInt(2)+1;
        /*
         * 为了各函数的统一性，此处也用for循环实现 int lastPos1 = (int) (80 * random()); String lastName =
         * lastNameArray[lastPos1]; if (lastLen == 2) { int lastPos2 = (int) (80 *
         * random()); lastName = lastName + lastNameArray[lastPos2]; }
         */
        for (int i = 0; i < lastLen; i++) {
            int lastPos = rd.nextInt(81);
            name.append(lastNameArray[lastPos]);
        }
        return name.toString();
    }

}

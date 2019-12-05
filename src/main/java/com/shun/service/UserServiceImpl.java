package com.shun.service;

import com.shun.annotation.UserAddAnnotation;
import com.shun.annotation.UserUpdateAnnotation;
import com.shun.dao.UserDao;
import com.shun.entity.User;
import com.shun.entity.UserLocation;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findAll(Integer page, Integer rows) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        List<User> users = userDao.selectByRowBounds(new User(), new RowBounds(start, rows));
        int records = userDao.selectCount(new User());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public Map findByExample(String searchField, String searchString, String searchOper, Integer page, Integer rows) {
        Example example = new Example(User.class);
        Map map = new HashMap();
        int start = (page-1)*rows;
        searchString=searchField.equals("status")?(searchString.equals("展示")?"1":(searchString.equals("冻结")?"2":searchString)):searchString;
        searchString=searchField.equals("status")&&searchOper.equals("cn")?(searchString.equals("展")||searchString.equals("示")?"1":(searchString.equals("冻")||searchString.equals("结")?"2":searchString)):searchString;
        switch (searchOper){
            case "cn":example.createCriteria().andLike(searchField,"%"+searchString+"%");break;
            case "eq":example.createCriteria().andEqualTo(searchField,searchString);break;
            case "ne":example.createCriteria().andNotEqualTo(searchField,searchString);break;

        }
        List<User> users = userDao.selectByExampleAndRowBounds(example, new RowBounds(start, rows));
        int records = userDao.selectCountByExample(example);
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("records",records);
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findById(String id) {
        return userDao.selectByPrimaryKey(new User().setId(id));
    }

    @Override
    @UserUpdateAnnotation("修改用户")
    public Map modify(User user) {
        Map map = new HashMap();
        userDao.updateByPrimaryKeySelective(user);
        map.put("status",200);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findNewUsers() {
        Map map = new HashMap();
        //当天用户注册数
        Example example = new Example(User.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),0, 0, 0);
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","男");
        int countTodayMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","女");
        int countTodayFeman = userDao.selectCountByExample(example);
        //近一周用户注册数
        example.clear();
        calendar.add(Calendar.DATE,-7);
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","男");
        int countWeekMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","女");
        int countWeekFeman = userDao.selectCountByExample(example);
        //近一月用户注册数
        example.clear();
        calendar.add(Calendar.MONTH,-1);
        calendar.add(Calendar.DATE,7);
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","男");
        int countMonthMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","女");
        int countMonthFeman = userDao.selectCountByExample(example);
        //近一年用户注册数
        example.clear();
        calendar.add(Calendar.YEAR,-1);
        calendar.add(Calendar.MONTH,1);
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","男");
        int countYearMan = userDao.selectCountByExample(example);
        example.clear();
        example.createCriteria().andGreaterThanOrEqualTo("createTime",calendar.getTime()).andEqualTo("sex","女");
        int countYearFeman = userDao.selectCountByExample(example);

        List listMan = Arrays.asList(countTodayMan,countWeekMan,countMonthMan,countYearMan);
        List listFeman = Arrays.asList(countTodayFeman,countWeekFeman,countMonthFeman,countYearFeman);

        map.put("listMan",listMan);
        map.put("listFeman",listFeman);
        map.put("status",200);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findUserCity() {
        Map map = new HashMap();
        List<UserLocation> userLocations = userDao.selectCityAndLocation();
        map.put("cities",userLocations);
        map.put("status",200);
        return map;
    }

    @Override
    @UserAddAnnotation("用户添加")
    @UserUpdateAnnotation("用户添加")
    public void addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        userDao.insertSelective(user);
    }

    @Override
    public Map doLogin(User user) {
        Map map = new HashMap();
        User reUser = userDao.selectOne(new User().setTelphone(user.getTelphone()));
        if(reUser==null||!reUser.getPassword().equals(user.getPassword())){
            map.put("status",-200);
            map.put("msg","用户名或密码错误");
        }else{
            map.put("status",200);
            map.put("user",reUser);
        }
        return map;
    }

    @Override
    public Map doRegister(User user, String code) {
        String reCode = stringRedisTemplate.opsForValue().get(user.getTelphone());
        Map map = new HashMap();
        if (code.equals(reCode)) {
            map.put("status",200);
            map.put("message","注册成功");
            addUser(user);
        }else{
            map.put("status",-200);
            map.put("message","注册失败");
        }
        return map;
    }

    @Override
    public Map doUpdateInfo(User user) {
        Map map = new HashMap();
        int i = userDao.updateByPrimaryKeySelective(user);
        if(i>0){
            User reUser = userDao.selectByPrimaryKey(user);
            map.put("status", 200);
            map.put("user",reUser);
        }else {
            map.put("status", -200);
            map.put("message", "更新失败");
        }
        return map;
    }

    @Override
    public Map getFriend(String uid) {
        Map map = new HashMap();
        map.put("status",200);
        try {
            SetOperations<String, String> sset = stringRedisTemplate.opsForSet();
            Set<String> guruIds = sset.members(uid);
            List<User> friends = new ArrayList<>();
            if (guruIds.size() == 0) {
                Random random = new Random();
                int nums = random.nextInt(5)+1;
                int i = userDao.selectCount(new User());
                int start = random.nextInt(i);
                List<User> users = userDao.selectByRowBounds(new User(), new RowBounds(start, nums));
                friends = users;
            } else {
                List<String> ids = Arrays.asList(guruIds.toArray(new String[guruIds.size()]));
                Set<String> m = sset.members(ids.get(0));
                for (int i = 1; i < ids.size(); i++) {
                    m = sset.intersect(ids.get(i), m);
                }
                m.remove(uid);
                Example example = new Example(User.class);
                List<String> ms = Arrays.asList(m.toArray(new String[m.size()]));
                if(ms.size()>0) {
                    example.createCriteria().andIn("id", ms);
                    friends = userDao.selectByExample(example);
                }else{
                    friends = null;
                }
            }

            map.put("list",friends);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",-200);
            map.put("message","获取金刚道友失败");
        }
        return map;
    }


}

package com.shun.service;

import com.shun.annotation.LogAnnotation;
import com.shun.dao.ManagerDao;
import com.shun.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @LogAnnotation(value = "登录后台")
    public Manager login(Manager manager) {
        if (manager==null||manager.getUsername()==null) {
            return null;
        }
        Manager reManager = managerDao.selectOne(new Manager().setUsername(manager.getUsername()));
        if(reManager!=null&&reManager.getPassword().equals(manager.getPassword())){
            return reManager;
        }else {
            throw new RuntimeException("登录失败！");
        }
    }

    @Override
    @LogAnnotation(value = "退出登录")
    public Map logout(HttpServletRequest request) {
        request.getSession().invalidate();
        Map map = new HashMap();
        map.put("status",200);
        return map;
    }
}

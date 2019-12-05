package com.shun.service;

import com.shun.entity.Manager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ManagerService {

    Manager login(Manager manager);
    Map logout(HttpServletRequest request);

}

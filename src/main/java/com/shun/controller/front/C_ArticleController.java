package com.shun.controller.front;

import com.shun.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("articlePage")
public class C_ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("detail")
    public Map detail(String uid,String id) {
        return articleService.findById(id);
    }
}

package com.shun.controller;

import com.shun.entity.Guru;
import com.shun.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @RequestMapping("findAll")
    public List<Guru> findAll() {
        return guruService.findAll();
    }
}

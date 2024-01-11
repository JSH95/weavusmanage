package com.example.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("weavusmanager/")
public class WeavusController {

    @GetMapping("list")
    private String list(){
        return "main";
    }//세션 사용 방법 (4
}

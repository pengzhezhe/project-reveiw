package com.pzz.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String indexView() {
        return "admin/index";
    }

    @GetMapping("/admin/user")
    public String userView() {
        return "admin/user/index";
    }
}

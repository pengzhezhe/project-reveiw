package com.pzz.review.controller;

import com.pzz.review.dto.UserDTO;
import com.pzz.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String indexView() {
        return "admin/index";
    }

    @GetMapping("/admin/user")
    public String userView() {
        return "admin/user/index";
    }

    @GetMapping("/admin/user/add")
    public String userAddView() {
        return "admin/user/add";
    }

    @GetMapping("/admin/user/update/{id}")
    public String userUpdateView(@PathVariable("id") int userId, Model model) {
        UserDTO user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "admin/user/update";
    }
}

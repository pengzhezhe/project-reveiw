package com.pzz.review.controller;

import com.pzz.review.domain.User;
import com.pzz.review.dto.ResponseDTO;
import com.pzz.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView() {
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/user/update")
    public String updateUserView(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update")
    @ResponseBody
    public ResponseDTO<String> updateUser(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String email = map.get("email");
        String name = map.get("name");
        Integer sex = Integer.parseInt(map.get("sex"));
        String username = (String) httpSession.getAttribute("username");
        User user = userService.getUserByUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setSex(sex);
        userService.updateUser(user);
        return new ResponseDTO<>(1, "修改成功", null);
    }

    @PostMapping("/user/update/password")
    @ResponseBody
    public ResponseDTO<String> updatePassword(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String password = map.get("password");
        String newPassword = map.get("new_password");
        String username = (String) httpSession.getAttribute("username");
        userService.changePassword(username, password, newPassword);
        return new ResponseDTO<>(1, "修改成功", null);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseDTO<String> login(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String username = map.get("username");
        String password = map.get("password");
        userService.login(username, password);
        httpSession.setAttribute("username", username);
        return new ResponseDTO<String>(1, "登录成功", null);
    }

}

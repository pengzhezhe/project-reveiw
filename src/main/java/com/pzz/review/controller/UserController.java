package com.pzz.review.controller;

import com.pzz.review.ao.RegisterAO;
import com.pzz.review.ao.UserAO;
import com.pzz.review.ao.UserAddAO;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.ResponseDTO;
import com.pzz.review.dto.UserDTO;
import com.pzz.review.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    @ResponseBody
    public ResponseDTO<Integer> login(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String username = map.get("username");
        String password = map.get("password");
        int userType = userService.login(username, password);
        httpSession.setAttribute("username", username);
        return new ResponseDTO<>(0, "登录成功", userType);
    }

    @GetMapping("/register")
    public String registerView() {
        return "user/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseDTO<String> register(@RequestBody RegisterAO registerAO) {
        System.out.println(registerAO);
        UserAddAO userAddAO = new UserAddAO();
        BeanUtils.copyProperties(registerAO, userAddAO);
        userAddAO.setUserType(0);
        if (userService.addUser(userAddAO))
            return new ResponseDTO<>(0, "注册成功", null);
        else
            return new ResponseDTO<>(1, "注册失败", null);
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/user/update")
    public String updateUserView(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        UserDTO user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user")
    @ResponseBody
    public ResponseDTO<String> addUser(@RequestBody UserAddAO userAddAO) {
        if (userService.addUser(userAddAO))
            return new ResponseDTO<>(0, "添加用户成功", null);
        else
            return new ResponseDTO<>(0, "添加失败", null);
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseDTO<PageDTO<UserDTO>> listUsers(@RequestParam(defaultValue = "1", name = "page") int pageNum, @RequestParam(defaultValue = "10", name = "limit") int pageSize) {
        PageDTO<UserDTO> pageDTO = userService.listUsers(0, pageNum, pageSize);
        return new ResponseDTO<>(0, "Success", pageDTO);
    }

    @PutMapping("/user")
    @ResponseBody
    public ResponseDTO<String> updateUser(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String email = map.get("email");
        String name = map.get("name");
        Integer sex = Integer.parseInt(map.get("sex"));
        UserAO userAO = new UserAO(username, email, name, sex);
        userService.updateUser(userAO);
        return new ResponseDTO<>(0, "修改成功", null);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResponseDTO<String> deleteUser(@PathVariable("id") int userId) {
        if (userService.deleteUser(userId))
            return new ResponseDTO<>(0, "删除成功", null);
        else
            return new ResponseDTO<>(1, "删除失败", null);
    }

    /**
     * 更新用户信息
     *
     * @param map         用户信息
     * @param httpSession Session
     * @return response
     */
    @PostMapping("/user/update")
    @ResponseBody
    public ResponseDTO<String> updateUser(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String email = map.get("email");
        String name = map.get("name");
        Integer sex = Integer.parseInt(map.get("sex"));
        String username = (String) httpSession.getAttribute("username");
        UserAO userAO = new UserAO(username, email, name, sex);
        userService.updateUser(userAO);
        return new ResponseDTO<>(0, "修改成功", null);
    }


    /**
     * 修改密码
     *
     * @param map         密码
     * @param httpSession Session
     * @return response
     */
    @PostMapping("/user/update/password")
    @ResponseBody
    public ResponseDTO<String> updatePassword(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String password = map.get("password");
        String newPassword = map.get("new_password");
        String username = (String) httpSession.getAttribute("username");
        userService.changePassword(username, password, newPassword);
        return new ResponseDTO<>(0, "修改成功", null);
    }
}

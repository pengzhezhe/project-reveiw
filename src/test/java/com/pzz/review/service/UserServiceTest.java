package com.pzz.review.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzz.review.domain.User;
import com.pzz.review.dto.UserDTO;
import com.pzz.review.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void login() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getUser() {
        UserDTO userDTO = userService.getUserByUsername("pengzhezhe");
        System.out.println(userDTO);
    }

    @Test
    public void listUsers() {
        PageHelper.startPage(1, 10);
        List<User> users = userMapper.listUsersByUserType(0);
        PageInfo pageInfo = new PageInfo(users);
        System.out.println(pageInfo);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u : users) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(u, userDTO);
            userDTOS.add(userDTO);
        }
        System.out.println(userDTOS);
        System.out.println(userDTOS.size());
    }
}
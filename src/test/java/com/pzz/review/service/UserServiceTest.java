package com.pzz.review.service;

import com.pzz.review.domain.User;
import com.pzz.review.dto.ResponseDTO;
import com.pzz.review.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

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
        User pengzhezhe = userService.getUserByUsername("pengzhezhe");
        System.out.println(pengzhezhe);
    }

    @Test
    public void listUsers() {
    }
}
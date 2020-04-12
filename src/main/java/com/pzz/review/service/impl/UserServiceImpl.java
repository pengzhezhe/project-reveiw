package com.pzz.review.service.impl;

import com.pzz.review.domain.User;
import com.pzz.review.exception.AppException;
import com.pzz.review.mapper.UserMapper;
import com.pzz.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null)
            throw new AppException("用户名不存在");
        if (!password.equals(user.getPassword()))
            throw new AppException("用户名或密码错误");
        return true;
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean addUser(User user) {
        if (userMapper.getUserByUsername(user.getUsername()) != null)
            throw new AppException("用户名已存在");
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return userMapper.delete(userId) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        if (userMapper.getUserByUsername(user.getUsername()) == null)
            throw new AppException("用户不存在");
        return userMapper.update(user) > 0;
    }

    @Override
    public boolean changePassword(String username, String password, String newPassword) {
        User user = userMapper.getUserByUsername(username);
        if (user == null)
            throw new AppException("用户不存在");
        if (!password.equals(user.getPassword()))
            throw new AppException("密码不正确");
        user.setPassword(newPassword);
        return userMapper.update(user) > 0;
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.getUser(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        return getUserByUsername(username).getId();
    }

    @Override
    public List<User> listUsers(Integer userType) {
        return userMapper.listUsers(userType);
    }
}

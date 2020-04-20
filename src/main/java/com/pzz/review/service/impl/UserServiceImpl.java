package com.pzz.review.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzz.review.ao.UserAO;
import com.pzz.review.ao.UserAddAO;
import com.pzz.review.domain.User;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.UserDTO;
import com.pzz.review.exception.AppException;
import com.pzz.review.mapper.UserMapper;
import com.pzz.review.service.UserService;
import com.pzz.review.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(UserAddAO userAddAO) {
        if (userMapper.getUserByUsername(userAddAO.getUsername()) != null)
            throw new AppException("用户名已存在");
        User user = new User();
        BeanUtils.copyProperties(userAddAO, user);
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return userMapper.delete(userId) > 0;
    }

    @Override
    public boolean updateUser(UserAO userAO) {
        User user = userMapper.getUserByUsername(userAO.getUsername());
        if (user == null)
            throw new AppException("用户不存在");
        user.setName(userAO.getName());
        user.setSex(userAO.getSex());
        user.setEmail(userAO.getEmail());
        return userMapper.update(user) > 0;
    }

    @Override
    public UserDTO getUser(Integer userId) {
        User user = userMapper.getUser(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public PageDTO<UserDTO> listUsers(Integer userType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.listUsers(userType);
        PageInfo pageInfo = new PageInfo(users);
        List<UserDTO> userDTOS = new ArrayList<>();
        CommonUtils.copyListProperties(users, userDTOS, UserDTO.class);
        return new PageDTO<>(userDTOS, pageInfo.getTotal());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        return getUserByUsername(username).getId();
    }

    @Override
    public int login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null)
            throw new AppException("用户名不存在");
        if (!password.equals(user.getPassword()))
            throw new AppException("用户名或密码错误");
        return user.getUserType();
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
}

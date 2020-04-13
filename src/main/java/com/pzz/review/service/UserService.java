package com.pzz.review.service;

import com.pzz.review.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return boolean
     */
    boolean login(String username, String password);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean addUser(User user);

    /**
     * 删除用户
     *
     * @param userId userId主键
     * @return boolean
     */
    boolean deleteUser(Integer userId);

    /**
     * 更新用户信息
     *
     * @param user user
     * @return boolean
     */
    boolean updateUser(User user);

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param password    旧密码
     * @param newPassword 新密码
     * @return boolean
     */
    boolean changePassword(String username, String password, String newPassword);

    /**
     * 根据userId查询用户
     *
     * @param userId userId
     * @return User
     */
    User getUser(Integer userId);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return User
     */
    User getUserByUsername(String username);

    /**
     * 通过用户名查找userId
     *
     * @param username
     * @return
     */
    Integer getUserIdByUsername(String username);

    /**
     * 根据userType批量查询用户
     *
     * @param userType userType
     * @return List<User>
     */
    List<User> listUsers(Integer userType);

}

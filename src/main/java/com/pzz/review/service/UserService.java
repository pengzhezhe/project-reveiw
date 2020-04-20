package com.pzz.review.service;

import com.pzz.review.ao.UserAO;
import com.pzz.review.ao.UserAddAO;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.UserDTO;

public interface UserService {
    /**
     * 添加用户
     *
     * @param userAddAO 用户信息
     * @return boolean
     */
    boolean addUser(UserAddAO userAddAO);

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
     * @param userAO UserAO
     * @return boolean
     */
    boolean updateUser(UserAO userAO);

    /**
     * 根据userId查询用户
     *
     * @param userId userId
     * @return User
     */
    UserDTO getUser(Integer userId);

    /**
     * 根据userType批量查询用户
     *
     * @param userType userType
     * @return List<UserDTO>
     */
    PageDTO<UserDTO> listUsers(Integer userType, Integer pageNum, Integer pageSize);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return UserDTO
     */
    UserDTO getUserByUsername(String username);

    /**
     * 通过用户名查找userId
     *
     * @param username username
     * @return userId
     */
    Integer getUserIdByUsername(String username);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return userType
     */
    int login(String username, String password);

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param password    旧密码
     * @param newPassword 新密码
     * @return boolean
     */
    boolean changePassword(String username, String password, String newPassword);
}

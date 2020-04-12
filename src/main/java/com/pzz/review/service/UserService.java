package com.pzz.review.service;

import com.pzz.review.domain.User;
import com.pzz.review.dto.ResponseDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    boolean login(String username, String password);

    boolean register(User user);

    boolean addUser(User user);

    boolean deleteUser(Integer userId);

    boolean updateUser(User user);

    boolean changePassword(String username,String password,String newPassword);

    User getUser(Integer userId);

    User getUserByUsername(String username);

    Integer getUserIdByUsername(String username);

    List<User> listUsers(Integer userType);

}

package com.pzz.review.mapper;

import com.pzz.review.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int delete(Integer id);

    int insert(User user);

    User getUserByUsername(String username);

    User getUser(Integer id);

    List<User> listUsers(Integer userType);

    int update(User user);
}
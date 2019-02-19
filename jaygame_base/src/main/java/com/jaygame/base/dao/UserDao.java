package com.jaygame.base.dao;

import com.jaygame.base.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    public User findDistinctByUsernameAndPassword(String username, String password);
    public User findByUsername(String username);
    public User findByPassword(String password);
    //public User findByUser_id(Integer userId);
}

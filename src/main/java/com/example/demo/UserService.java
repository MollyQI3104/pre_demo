package com.example.demo;

import java.util.List;

public interface UserService {

    User save(User userEntity);

    User findById(Integer id);

    User findByName(String name);

    User update(User user);

    Boolean delete(Integer id);

    List<User> findAll();

    User findUserByNameAndPassword(String name, String password);

}

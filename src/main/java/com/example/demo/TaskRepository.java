package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

import java.util.List;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findAllByUserIdOrderByCreateDateDesc(Integer userId);

    List<Task> findAllByUserIdAndTitle(Integer userId,  String title);

    List<Task> findAllByUserIdAndStatusOrderByCreateDateDesc(Integer userId,  String status);

    List<Task> findAllByUserIdAndStatusAndTitleLikeOrderByCreateDateDesc(Integer userId,  String status,String title);

    List<Task> findAllByUserIdAndTitleLikeOrderByCreateDateDesc(Integer userId, String title);

    List<Task> findAllByUserIdAndContentLikeOrderByCreateDateDesc(Integer userId, String content);


}



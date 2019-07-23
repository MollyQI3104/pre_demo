package com.example.demo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface TaskService {

    Task save(Task task);

    List<Task> findAll();

    List<Task> findByUserId(Integer userId);

    List<Task> findByTitle(Integer userId,String title);

    Task findById(Integer id);

    List<Task> findByStatus(Integer userId,  String status);

    List<Task> findByWord(Integer userId,  String word);

    List<Task> findByWordAndStatus(Integer userId, String status,String word);

    Task update(Task task);


}

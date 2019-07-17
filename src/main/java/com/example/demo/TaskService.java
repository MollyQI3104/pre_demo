package com.example.demo;

import java.util.List;

public interface TaskService {

    Task save(Task task);

    List<Task> findAll();

    List<Task> findByUserId(Integer userId);

    Task findById(Integer id);

    List<Task> findByStatus(Integer userId,  String status);

    List<Task> findByWord(Integer userId,  String word);

}

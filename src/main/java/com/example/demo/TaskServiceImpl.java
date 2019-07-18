package com.example.demo;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task){
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll(){
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Task findById(Integer id){
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> findByUserId(Integer userId){
        return taskRepository.findAllByUserIdOrderByCreateDateDesc(userId);
    }

    @Override
    public List<Task> findByStatus(Integer userId,  String status){
 //       return (List<Task>)taskRepository.findAllByUserIdAndStatus(userId,status);
        return taskRepository.findAllByUserIdAndStatusOrderByCreateDateDesc(userId,status);
    }

    @Override
    public List<Task> findByWord(Integer userId,  String word){
        //      return (List<Task>)taskRepository.findAllByUserIdAndTitle(userId,word);

        List<Task> list1 = taskRepository.findAllByUserIdAndTitleLikeOrderByCreateDateDesc(userId,"%"+word+"%");
        List<Task> list2 = taskRepository.findAllByUserIdAndContentLikeOrderByCreateDateDesc(userId,"%"+word+"%");

        list1.addAll(list2);

        return list1;


    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }
}

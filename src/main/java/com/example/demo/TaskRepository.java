package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findAllByUserId(Integer userId);

    List<Task> findAllByUserIdAndStatus(Integer userId,  String status);

    List<Task> findAllByUserIdAndTitle(Integer userId, String title);

    List<Task> findAllByUserIdAndContent(Integer userId, String content);

}


//    //@Query("from Task t where t.status=:status")
//    List<Task> findByStatus(String status);
//
//    List<Task> findByUserIdAndStatus(int userId,  String status);
//
// /*   @Query("from Task t where t.userId=:userId and  t.status=:status")
//    List<Task> findByUserIdStatus(@Param("userId") int userId, @Param("status") String status);*/
//
//    @Query("from Task t where t.id BETWEEN  :start and :end")
//    List<Task> findBetween(@Param("start") int start, @Param("end") int end);
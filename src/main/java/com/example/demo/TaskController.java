package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/publish")
public class TaskController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;


    //add a task
    @RequestMapping(value = "/add/submit", method = RequestMethod.POST)
    public @ResponseBody String addSubmitPage(ModelMap map, @RequestParam String title, @RequestParam String content,
                                @RequestParam String endDate,
                                HttpServletRequest request) {
//        try {
            Task task = new Task();
            task.setTitle(title);
            task.setStatus("PASSIVE");//default
            task.setContent(content);
            task.setUserId(getCurrentUser(request).getId());

            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            task.setCreateDate(sqlDate);

            task.setTaskDate(str2date(endDate));

            taskService.save(task);


        map.addAttribute("add", "success");
        return "saved";
//        return "redirect:/publish/list";
    }




    //edit task
    @RequestMapping(value = "/edit/submit", method = RequestMethod.GET)
    public @ResponseBody String editSubmitPage(ModelMap map, @RequestParam Integer id, @RequestParam String title, @RequestParam String content, @RequestParam String endDate) throws ParseException {


        Task task = taskService.findById(id);
        task.setTitle(title);

        task.setContent(content);

        task.setTaskDate(str2date(endDate));

        taskService.update(task);

        return "saved";
//        return "redirect:/publish/list";
    }

    //task_list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(ModelMap map,HttpServletRequest request) {
        List<Task> list = taskService.findByUserId(getCurrentUser(request).getId());
        map.addAttribute("list", list);

        return "list";
//        return "redirect:/publish/list";
    }

    //show task_list
    @GetMapping(path="/test_all")
    public @ResponseBody Iterable<Task> getAllTasks(HttpServletRequest request) {
        // This returns a JSON or XML with the users
        return taskService.findByUserId(getCurrentUser(request).getId());
    }

    //select_status
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String selectStatus(ModelMap map, @RequestParam String status,HttpServletRequest request) {
        List<Task> list = taskService.findByStatus(getCurrentUser(request).getId(),status);
        map.addAttribute("list", list);

        return "select";
//        return "list";
    }

    //show status_list
    @GetMapping(path="/test_status")
    public @ResponseBody Iterable<Task> getAllStatusTasks(@RequestParam String status,HttpServletRequest request) {
        // This returns a JSON or XML with the users
        return taskService.findByStatus(getCurrentUser(request).getId(),status);
    }

    //select_keyword
    @RequestMapping(value = "/select_word", method = RequestMethod.GET)
    public String selectWord(ModelMap map, @RequestParam String word,HttpServletRequest request) {
        List<Task> list = taskService.findByWord(getCurrentUser(request).getId(),word);
        map.addAttribute("list", list);

        return "select_word";
//        return "list";
    }


    //show keyword_list
    @GetMapping(path="/test_word")
    public @ResponseBody Iterable<Task> getAllWordTasks(@RequestParam String word,HttpServletRequest request) {
        // This returns a JSON or XML with the users
        return taskService.findByWord(getCurrentUser(request).getId(),word);
    }

//change status
//...

//    private User getCurrentUser() {
//
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (userDetails != null) {
//            User userEntity = userService.findByName(userDetails.getUsername());
//            return userEntity;
//        } else {
//            return null;
//        }
//    }



    private User getCurrentUser(HttpServletRequest request) {

            String name = (String)request.getSession().getAttribute("name");
            User userEntity = userService.findByName(name);
            return userEntity;

        }

        private java.sql.Date str2date(String time)
        {

            Date endTime = new Date();

            try {
                endTime = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date taskDate = new java.sql.Date(endTime.getTime());

            return taskDate;
        }


}

package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.valueOf;

@Controller
@RequestMapping(path = "/publish")
public class TaskController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;


    //add a task
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSubmitPage(ModelMap map,@ModelAttribute("task") Task task,@ModelAttribute("SearchTask") Task searchtask,
                                HttpServletRequest request) {

        List<Task> list  = taskService.findByTitle(getCurrentUser(request).getId(),task.getTitle());
        if (list.size()!=0) {
            map.addAttribute("result", "The same task title is already existed.");

            List<Task> tasks = taskService.findByUserId(getCurrentUser(request).getId());
            map.addAttribute("list", tasks);

            return "index";
        } else {

            task.setStatus("TODO");//default
            task.setUserId(getCurrentUser(request).getId());

            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            task.setCreateDate(sqlDate);

//           task.setTaskDate(str2date(endDate));

            taskService.save(task);

            return "redirect:/publish/list";
        }

    }


    //edit task
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public  String editPage(ModelMap map, @RequestParam Integer id,
                                               @ModelAttribute("task") Task task,
                            @ModelAttribute("SearchTask") Task searchtask)  {


        task = taskService.findById(id);
        map.addAttribute("task", task);

        return "update";

    }



    //submit task
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public  String editSubmitPage(@ModelAttribute("task") Task task,@ModelAttribute("SearchTask") Task searchtask) {


        Task taskEntity = taskService.findById(task.getId());
        taskEntity.setTitle(task.getTitle());

        taskEntity.setTaskDate(task.getTaskDate());

        taskService.update(taskEntity);

        return "redirect:/publish/list";

    }



    //task_list（home page）
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(ModelMap map,HttpServletRequest request,@ModelAttribute("task") Task task,
                           @ModelAttribute("SearchTask") Task searchtask) {
        List<Task> list = taskService.findByUserId(getCurrentUser(request).getId());
        map.addAttribute("list", list);

        return "index";

    }


//    //select_status
//    @RequestMapping(value = "/select", method = RequestMethod.GET)
//    public String selectStatus(ModelMap map, @RequestParam String status,HttpServletRequest request) {
//        List<Task> list = taskService.findByStatus(getCurrentUser(request).getId(),status);
//        map.addAttribute("list", list);
//
//        return "select";
//
//    }

    //select_keyword
    @RequestMapping(value = "/select_word", method = RequestMethod.GET)
    public String selectWord(ModelMap map, HttpServletRequest request,@ModelAttribute("SearchTask") Task task) {

        List<Task> list;

        String word = task.getTitle();
        String status;

        if(task.getStatus()==null)
            status = "BOTH";
        else status = task.getStatus();

        if(word.equals(""))
        {
            if(status.equals("BOTH"))
                list = taskService.findByUserId(getCurrentUser(request).getId());
            else
                list = taskService.findByStatus(getCurrentUser(request).getId(),status);

        }
        else
        {
            if(status.equals("BOTH") || status.equals(""))
                list = taskService.findByWord(getCurrentUser(request).getId(),word);
            else
                list = taskService.findByWordAndStatus(getCurrentUser(request).getId(),status,word);
        }


        if(list.isEmpty())
        {
            map.addAttribute("list", list);
            map.addAttribute("result", false);
            return "select_word";
        }
        map.addAttribute("result", true);
        map.addAttribute("list", list);
        map.addAttribute("keyword", word);

        return "select_word";
    }




    //change status
    @RequestMapping(value = "/change_status", method = RequestMethod.POST)
    public @ResponseBody String changeStatus(
            @RequestParam String id,

                            @ModelAttribute("task") Task task,
                            @ModelAttribute("SearchTask") Task searchtask)  {

        Integer task_id = null;
        if(id!=null)
           task_id = valueOf(id);
        else
            return "null id";

        Task taskEntity = taskService.findById(task_id);
        if(taskEntity.getStatus().equals("TODO"))
            taskEntity.setStatus("DONE");
        else
            taskEntity.setStatus("TODO");

        taskService.update(taskEntity);

        return "success";


    }




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

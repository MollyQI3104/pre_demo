package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String password) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User n = new User();
		n.setName(name);
		n.setPassword(password);
		userRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@RequestMapping(value = "/thy_test", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber)
	{
		int page =1 ;

		User user = userRepository.findByName("mo1");

		//分页查询数据
		model.addAttribute("page", page);
		//查询条件
		model.addAttribute("user", user);
		//页面标题
		model.addAttribute("title", "用户管理");
		//转到待渲染模板，所有模板都在templates文件夹下，users/list指templates文件夹下的users文件夹下的list.html页面。
		return "list";
	}
}


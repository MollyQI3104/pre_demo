package com.example.demo;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /user (after Application path)
public class UserController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping(path = "/index")
	public String firstPage(@ModelAttribute("user") User user) {

		return "register";
	}

	@GetMapping(path = "/index_login")
	public String Page(@ModelAttribute("user") User user) {

		return "login";
	}

	//register
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public  String addNewUser (ModelMap map, @ModelAttribute("user") User user) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User userEntity = userService.findByName(user.getName());
		if (userEntity != null) {
			map.addAttribute("result", "This user name has been used.");
			return "register";
		} else {

			String MD5Password = MD5Util.getMD5(user.getPassword());
			user.setPassword(MD5Password);

			userService.save(user);
			return "login";
		}

	}

	// login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(ModelMap map, @ModelAttribute("user") User user,HttpServletRequest request) {

		String MD5Password = MD5Util.getMD5(user.getPassword());
		User userEntity = userService.findUserByNameAndPassword(user.getName(), MD5Password);
		if (userEntity == null) {
			map.addAttribute("login_error", "Wrong name or password!");
			return "login";
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("name",user.getName());
			return "redirect:/publish/list";

		}
	}


	@GetMapping(path = "/logout")
	public String logoutPage(@ModelAttribute("user") User user,HttpServletRequest request) {

		request.getSession().removeAttribute("name");

		return "login";
	}

	private User getCurrentUser(HttpServletRequest request) {

		String name = (String)request.getSession().getAttribute("name");
		User userEntity = userService.findByName(name);
		return userEntity;

	}
}

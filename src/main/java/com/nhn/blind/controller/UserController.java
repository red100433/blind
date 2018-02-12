package com.nhn.blind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhn.blind.model.User;
import com.nhn.blind.service.UserService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/signup")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
//	@Autowired
//	private RoleDao roleDao;

	 	
	@GetMapping("")
	public Mono<String> home() {
		return Mono.just("/user/signup");
	}
//	
//	//미구현
//	@GetMapping("/{id}")
//	public ModelAndView home(Model model, @PathVariable String id) {
//		ModelAndView mv = new ModelAndView("/user/userUpdate");
//		log.info("request GetMapping Id: {}", id);
//		model.addAttribute("id", id);
//		return mv;
//	}
	
	@PostMapping("/user")
	public Mono<String> addUser(@RequestBody User user) {
		log.info("{}", user.toString());
		Mono<User> usert = userService.getById(user.getId());
//		User user2 = User.of("insertData", "insertData@naver.com", "insert");
//		userService.add(user);
//		roleDao.add(user.getEmail());
		return Mono.just("success");
	}
	
	@DeleteMapping("/user")
	@ResponseBody
	public Mono<String> deleteUser(@RequestBody User user) {
		log.info("User id:{}", user.getId());
//		userService.delete(user.getId());
		return Mono.just("");
	}
	
	@PutMapping("/user") // change Name Email Password
	@ResponseBody
	public Mono<String> updateUser(@RequestBody User updateUser) {
		log.info("{}", updateUser.toString());
		
		Mono<User> user = userService.getById(updateUser.getId());
		log.info("{}", user.toString());
		
//		updateUser.setName(user.getName());
//		updateUser.setPassword(user.getPassword());
//		userService.update(updateUser);
		return Mono.just("putSuccess");
	}
	
	
	//TODO admin 권한으로 이동
//	@GetMapping("/view")
//	public ModelAndView allUser(Model model) {
//		ModelAndView mv = new ModelAndView("/user/userView");
//		model.addAttribute("users", userService.getUserList());
//		return mv;
//	}
}
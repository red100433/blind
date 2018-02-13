package com.nhn.blind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	@PostMapping("")
	public Mono<String> addUser(@ModelAttribute User user) {
		log.info("{}", user.toString());
		userService.add(user);
		return Mono.just("redirect:/login");
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
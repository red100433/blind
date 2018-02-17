package com.nhn.blind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	 	
	@GetMapping("")
	public Mono<String> home() {
		return Mono.just("/user/signup");
	}
	
	@GetMapping("/{id}")
	public Mono<String> home(Model model, @PathVariable String id) {
		log.info("request GetMapping Id: {}", id);
		model.addAttribute("id", id);
		return Mono.just("/user/userUpdate");
	}
	
	@GetMapping("/userUpdate")
	public Mono<String> userUpdate(Model model, @CookieValue("userId") String userId) {
		model.addAttribute("userId", userId);
		return Mono.just("/user/userUpdate");
	}
	
	
	@PostMapping("")
	public Mono<String> addUser(@ModelAttribute User user) {
		log.info("{}", user.toString());
		userService.add(user);
		return Mono.just("redirect:/login");
	}
	
	
	
	//TODO cookie 값 삭제 필요
	@DeleteMapping("/{userId}")
	@ResponseBody
	public Mono<Void> deleteUser(@PathVariable String userId) {
		log.info("User id:{}", userId);
//		userService.delete(user.getId());
		return Mono.empty();
	}
	
	//TODO cookie 값 삭제 필요
	@PutMapping("/user") // change Name Email Password
	@ResponseBody
	public Mono<String> updateUser(@RequestBody User updateUser) {
		log.info("{}", updateUser.toString());
		
		userService.add(updateUser);
		return Mono.just("putSuccess");
	}
	
	
	//TODO admin 권한으로 이동
//	@GetMapping("/view")
//	public Mono<String> allUser(Model model) {
//		model.addAttribute("users", userService.getUserList());
//		return Mono.just("/user/userView");
//	}
}
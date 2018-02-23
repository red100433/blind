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

import reactor.core.publisher.Mono;

/**
 * 
 * @author daeyun.jang
 *
 */
@Controller
@RequestMapping("/signup")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public Mono<String> home() {
		return Mono.just("/user/signup");
	}

	@GetMapping("/{id}")
	public Mono<String> home(Model model, @PathVariable String id) {
		model.addAttribute("id", id);
		return Mono.just("/user/userUpdate");
	}

	@GetMapping("/userUpdate")
	public Mono<String> updatePage(Model model, @CookieValue("userId") String userId) {
		model.addAttribute("userId", userId);
		return Mono.just("/user/userUpdate");
	}

	@PostMapping
	public Mono<String> addUser(@ModelAttribute User user) {
		userService.add(user);
		return Mono.just("redirect:/login");
	}

	@DeleteMapping("/{userId}")
	@ResponseBody
	public Mono<Void> deleteUser(@PathVariable String userId) {
		return userService.delete(Integer.parseInt(userId));
	}

	@PutMapping("/user")
	@ResponseBody
	public Mono<String> updateUser(@RequestBody User updateUser) {
		userService.add(updateUser);
		return Mono.just("putSuccess");
	}
}
package com.nhn.blind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nhn.blind.model.User;
import com.nhn.blind.service.UserService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("login")
@Slf4j
public class LoginController {
	@Autowired
	private UserService userService;
	
    @GetMapping("")
    public Mono<String> login() {
        return Mono.just("/login");
    }
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> loginUser(@ModelAttribute User user, ServerHttpResponse res){
    	log.info("{}", user);
    	Mono<User> getByEmail = userService.getByEmail(user.getEmail())
    			.filter(s -> s.getPassword().equals(user.getPassword()));
    	if(getByEmail.blockOptional().isPresent()) {
    		ResponseCookieBuilder userEmail = ResponseCookie.from("userEmail", user.getEmail());
    		userEmail.path("/");
    		res.addCookie(userEmail.build());
    		return Mono.just("/home");
    	} else {
    		return Mono.just("/login");
    	}
    }
}

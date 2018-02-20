package com.nhn.blind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
@Slf4j
public class LoginController {
	@Autowired
	private UserService userService;
	
    @GetMapping("login")
    public Mono<String> login() {
        return Mono.just("login");
    }
    
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> loginUser(Model model, @ModelAttribute User user, ServerHttpResponse res){
    	log.info("{}", user);
    	Mono<User> getByEmail = userService.getByEmail(user.getEmail())
    			.filter(s -> s.getPassword().equals(user.getPassword()));
    	if(getByEmail.blockOptional().isPresent()) {
    		ResponseCookieBuilder userId = ResponseCookie.from("userId", String.valueOf(getByEmail.block().getId()));
    		userId.path("/");
    		res.addCookie(userId.build());
    		return Mono.just("redirect:/");
    	} else {
    		return Mono.just("/login");
    	}
    }
    
    @GetMapping("logout")
    public Mono<String> logout(@CookieValue("userId") String userId, ServerHttpRequest req, ServerHttpResponse res) {
    	//쿠키 삭제
    	ResponseCookieBuilder setUserId = ResponseCookie.from("userId", "");
		setUserId.path("/");
		res.addCookie(setUserId.build());
    	return Mono.just("redirect:/login");
    }
}

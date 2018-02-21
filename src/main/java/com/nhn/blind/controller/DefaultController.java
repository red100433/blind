package com.nhn.blind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nhn.blind.cache.CommentCache;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class DefaultController {
	@Autowired
	private CommentCache commentCache;
	
	@GetMapping("/")
    public Mono<String> home() throws InterruptedException {
		commentCache.init();
        return Mono.just("redirect:/view");
    }
}

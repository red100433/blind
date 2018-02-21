package com.nhn.blind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Mono;

@Controller
public class DefaultController {
	@GetMapping("/")
    public Mono<String> home() throws InterruptedException {
        return Mono.just("redirect:/view");
    }
}

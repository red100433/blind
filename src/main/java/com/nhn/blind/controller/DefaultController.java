package com.nhn.blind.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.nhn.blind.cache.CommentCache;

import io.netty.handler.codec.http.cookie.Cookie;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServerRequest;

@Controller
@Slf4j
public class DefaultController {
	@Autowired
	private CommentCache commentCache;
	@GetMapping("/")
    public Mono<String> home() {
		commentCache.init();
        return Mono.just("redirect:/view");
    }
}

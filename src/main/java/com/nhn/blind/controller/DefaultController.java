package com.nhn.blind.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;

import io.netty.handler.codec.http.cookie.Cookie;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServerRequest;

@Controller
@Slf4j
public class DefaultController {

	@GetMapping("/")
    public Mono<String> home() {
        return Mono.just("home");
    }
}

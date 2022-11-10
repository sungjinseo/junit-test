package dev.greatseo.template.apipage.controller;

import dev.greatseo.template.apipage.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    /**
     * rest api 형식이 아닌 일반 호출용...하지만 테스트용임
     */
    private final HomeService service;

    public HomeController(HomeService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public @ResponseBody String hello() {
        return "Hello, World";
    }

    @RequestMapping("/greeting")
    public @ResponseBody String greeting() {
        return service.greet();
    }
}

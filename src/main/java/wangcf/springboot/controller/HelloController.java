package wangcf.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangcf
 * @Date: 2019/4/28 22:13
 */
@RestController
public class HelloController {
    @RequestMapping("/")
    public String Index() {
        return "Greetings from Spring Boot!";
    }
}

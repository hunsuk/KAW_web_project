package webProject.SIProject.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "index";
    }
}

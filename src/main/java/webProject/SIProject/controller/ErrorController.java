package webProject.SIProject.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ErrorController {

    @GetMapping("/error/403")
    public String dispDenied() {
        return "/error/403";
    }
}

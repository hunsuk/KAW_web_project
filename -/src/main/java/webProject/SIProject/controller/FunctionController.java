package webProject.SIProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class FunctionController {
    @GetMapping("/publish_req")
    public String publish_req(){
        return "PalletRequest";
    }
    @GetMapping("/publish_resp")
    public String publish_resp(){
        return "PalletRespond";
    }

    @GetMapping("/QnA")
    public String QnA(){
        return "QnA";
    }

    @GetMapping("/FS")
    public String FS(){
        return "FSapi";
    }
}

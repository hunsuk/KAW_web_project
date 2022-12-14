package webProject.SIProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webProject.SIProject.domain.User;
import webProject.SIProject.dto.Front_main_DTO;
import webProject.SIProject.dto.User_DTO;
import webProject.SIProject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;


    @GetMapping("/")
    public String goToPage(@AuthenticationPrincipal User user,Model model){
        model.addAttribute("userName",user.getCorpName()+" "+user.getManagerName());
        model.addAttribute("userAuth",user.getAuth());
        return "main_v3";
//        if(user.getAuth().equals("ROLE_USER")){
//            return "main_v3";
//        }else{
//            return "admin";
//        }
    }


    @GetMapping("/login")
    public String loginPage(){
        //로그인 할 때 마다 timestamp 추가

        return "login";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }


    @PostMapping("/user")
    public String signup(User_DTO infoDto) { // 회원 추가
        log.info(infoDto.toString());
        userService.save(infoDto);
        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }


}

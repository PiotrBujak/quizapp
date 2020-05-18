package quizapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import quizapp.services.TestService;

@Controller
@CrossOrigin
@Scope(value = "session")
public class HomeController {

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("message", context.getAuthentication().getName());
        model.addAttribute("tests", testService.getTestsDto());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}

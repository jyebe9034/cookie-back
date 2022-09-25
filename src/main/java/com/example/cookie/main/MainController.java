package com.example.cookie.main;

import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController extends BaseController {

    @GetMapping(API_PREFIX + "/test/main")
    public String main(Model model) {
        model.addAttribute("data", "Hello World");
        return "main";
    }

    @GetMapping(API_PREFIX + "/test/login")
    public String login() {
        return "login";
    }

    @GetMapping(API_PREFIX + "/test/join")
    public String join() {
        return "join";
    }

    @GetMapping(API_PREFIX + "/test/board")
    public String board() {
        return "board";
    }
}

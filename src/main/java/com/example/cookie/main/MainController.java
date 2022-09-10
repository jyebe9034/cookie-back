package com.example.cookie.main;

import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController extends BaseController {

    private final String URI_PREFIX = API_PREFIX + "/main";

    @GetMapping(URI_PREFIX)
    public String main() {
        return "main";
    }
}

package com.example.cookie.test;

import com.example.cookie.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController extends BaseController {

    @GetMapping(API_PREFIX + "/test/{string}")
    public ResponseEntity<Integer> test(@PathVariable("string") String string) {
        log.info("====== test : " + string);
        if ("test".equals(string)) {
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.ok(0);
        }
    }
}

package com.apptester.app12;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class test1 {
    @GetMapping(path = "/t1")
    public String hel() {
        return "hello";

    }
}
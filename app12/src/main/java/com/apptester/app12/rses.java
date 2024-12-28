package com.apptester.app12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class rses {
    @GetMapping("/temp")
    public String getMethodName(@RequestParam int id) {
        return "temper" + id;

    }

    @GetMapping(path = "/hello/{username}")
    public String hell(@RequestParam int id, @PathVariable String username) {
        return "hello" + id + username;

    }

    @PostMapping(path = "/check")
    public checker check(@RequestParam String name) {
        checker c1 = new checker();
        c1.setName(name);
        return c1;

    }

}

class checker {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public checker(String name) {
        this.name = name;
    }

    public checker() {

    }

    @Override
    public String toString() {
        return "checker [name=" + name + "]";
    }

}

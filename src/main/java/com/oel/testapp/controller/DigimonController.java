package com.oel.testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DigimonController {

    @GetMapping("/all")
    public String getAll(){
        return "all";
    }

    @GetMapping("/name/{name}")
    public String getByName(@PathVariable String name){
        return name;
    }

    @GetMapping("/level/{level}")
    public String getByLevel(@PathVariable String level){
        return level;
    }
}

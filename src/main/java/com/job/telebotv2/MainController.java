package com.job.telebotv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    UrlRepos urlRepos;


    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<Url> urls = urlRepos.findAll();
        model.put("urls", urls);
        return "index";
    }

    @PostMapping
    public String add(@RequestParam String text, Map<String, Object> model){
        Url url = new Url(text, true);
        urlRepos.save(url);
        Iterable<Url> urls = urlRepos.findAll();
        model.put("urls", urls);
        return "redirect:/index";
    }
}

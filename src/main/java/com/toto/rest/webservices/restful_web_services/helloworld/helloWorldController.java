package com.toto.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorldController {

    @GetMapping(path = "/hello-world")
    public helloworld helloWorldBean() {
        return new helloworld("Hello World");
    }

    @GetMapping(path = "/hello-world/{name}")
    public helloworld helloWorldBeanPathVariable(@PathVariable String name) {
        return new helloworld(String.format("Hello World, %s", name));
    }



}

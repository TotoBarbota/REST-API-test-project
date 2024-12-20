package com.toto.rest.webservices.restful_web_services.helloworld;

import org.springframework.context.annotation.Bean;

public class helloworld {
    public String message;

    public helloworld(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "helloworld{" +
                "message='" + message + '\'' +
                '}';
    }
}

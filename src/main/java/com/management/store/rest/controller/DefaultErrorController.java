package com.management.store.rest.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = MediaType.TEXT_HTML_VALUE)
    public String handleError() {
        return """
                Oops, an error occurred!
                                
                Possible causes:
                                
                1. If running the unsecured version
                                               
                You've probably entered an invalid request path.
                Double check the spelling to see if it matches the API and try again.
                If the error still persists, please contact an administrator.
                                
                                
                2. If running the secured version
                                
                You are probably running the request without authentication.
                Please retry with a valid user and password.
                                
                Thank you..
                """;
    }

    @GetMapping(value = "/info", produces = MediaType.TEXT_HTML_VALUE)
    public String info() {
        return """
                NOTE!
                                
                This endpoint (http://localhost:8090/info) can be accessed without security.
                The same goes for the default error endpoint (http://localhost:8090/error).
                The swagger documentation (http://http://localhost:8090/swagger-ui/) is also free to access.
                                
                All others required user and password authentication if enabled.
                """;
    }
}

package com.management.store.rest.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return """
                <html>
                Oops, an error occurred!<br><br><br>
                                
                You've probably entered an invalid request path.<br>
                Double check the spelling to see if it matches the API and try again.<br>
                If the error still persists, please contact an administrator.<br><br><br>
                                
                Thank you..
                </html>
                """;
    }

    @GetMapping("/info")
    public String info() {
        return """
                <html>
                NOTE!<br><br>
                This endpoint can be accessed without security.<br>
                The same goes for the /error endpoint.<br>
                All others required user and password authentication if enabled.
                </html>
                """;
    }
}

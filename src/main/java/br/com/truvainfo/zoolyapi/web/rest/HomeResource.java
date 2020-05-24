package br.com.truvainfo.zoolyapi.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/manager")
    public String manager() {
        return ("<h1>Welcome Manager</h1>");
    }

    @GetMapping("/doctor")
    public String doctor() {
        return ("<h1>Welcome Doctor</h1>");
    }

    @GetMapping("/operator")
    public String user() {
        return ("<h1>Welcome Operator</h1>");
    }

}

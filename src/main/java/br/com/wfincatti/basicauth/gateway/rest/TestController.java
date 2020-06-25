package br.com.wfincatti.basicauth.gateway.rest;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping
    @ResponseBody
    public String getMessage() {
        return "{\"message\": \"USER\"}";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getMessageAdmin(){
        return "{\"message\": \"ADMIN\"}";
    }

}

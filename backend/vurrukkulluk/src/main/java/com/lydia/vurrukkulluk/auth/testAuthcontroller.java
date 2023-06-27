package com.lydia.vurrukkulluk.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/testAth")
public class testAuthcontroller {

    @GetMapping
    public String hi(){
        return "HIIII !!! This is secured";
    }
}

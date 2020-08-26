package com.shawf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author shawf_lee
 * @date 2020/7/15 15:08
 * Content:
 */
@Controller
public class MeController {

    @GetMapping("/me")
    public String me(){
        return "me";
    }
}

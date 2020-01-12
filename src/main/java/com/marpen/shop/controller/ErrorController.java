package com.marpen.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @RequestMapping(value="/error")
    public String handle(HttpServletRequest request, Model model) {
        model.addAttribute("status", request.getAttribute("javax.servlet.error.status_code"));
        model.addAttribute("reason", request.getAttribute("javax.servlet.error.message"));
        return "error";
    }
}

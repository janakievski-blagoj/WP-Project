package com.example.demo.web.controller;

import com.example.demo.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getManufacturerPage(Model model){
        model.addAttribute("manufacturers", this.manufacturerService.findAll());
        model.addAttribute("bodyContent","manufacturers");
        return  "master-template";
    }
}

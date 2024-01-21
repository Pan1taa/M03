package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(LaLigaController.LALIGA)
public class LaLigaController {
    public static final String LALIGA = "/laliga";

    @Autowired
    LaLigaService laLigaService;

    @GetMapping
    public String getSports(Model model) {
        LaLigaResponse[] sp = laLigaService.getLaLigaResponse();
        model.addAttribute("laliga", sp);
        return "laliga";
    }

}

package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(MLSController.MLS)
public class MLSController {
    public static final String MLS = "/mls";

    @Autowired
    MLSService mlsService;

    @GetMapping
    public String getSports(Model model) {
        MLSResponse[] sp = mlsService.getMLSResponse();
        model.addAttribute("mls", sp);
        return "mls";
    }

}

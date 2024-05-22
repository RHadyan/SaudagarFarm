package com.saudagarFarmer.kelompokArio.Controller;


import com.saudagarFarmer.kelompokArio.Models.ProductModels;
import com.saudagarFarmer.kelompokArio.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RedirectController {

    @Autowired
    private ProductRepository repoProduct;

    @GetMapping({"/","/home"})
    public String showHome(){
        return "/homepage";
    }

    @GetMapping("/about")
    public String showpage(){
        return "/about";
    }





}

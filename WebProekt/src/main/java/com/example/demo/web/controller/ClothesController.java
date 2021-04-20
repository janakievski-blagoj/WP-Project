package com.example.demo.web.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Clothes;
import com.example.demo.model.Manufacturer;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ClothesService;
import com.example.demo.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clothes")
public class ClothesController {

    private final ClothesService clothesService;
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;


    public ClothesController(ClothesService clothesService,
                             ManufacturerService manufacturerService,
                             CategoryService categoryService) {
        this.clothesService = clothesService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getClothesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Clothes> clothes = this.clothesService.findAll();
        model.addAttribute("clothes", clothes);
        model.addAttribute("bodyContent", "clothes");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String editArticlePage(@PathVariable Long id, Model model){
        if (this.clothesService.findById(id).isPresent()) {
            Clothes clothes = this.clothesService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("categories", categories);
            model.addAttribute("clothes", clothes);
            model.addAttribute("bodyContent", "add-article");
            return "master-template";
        }
        return "redirect:/clothes?error=ArticleNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addArticlePage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-article");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticlePage(@PathVariable Long id){
        this.clothesService.deleteById(id);
        return "redirect:/clothes";
    }

    @PostMapping("/add")
    public String saveArticle(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam String color,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer) {
        if (id != null) {
            this.clothesService.edit(id, name, price, color, quantity, category, manufacturer);
        } else {
            this.clothesService.save(name, price,  color, quantity, category, manufacturer);
        }
        return "redirect:/clothes";
    }
}




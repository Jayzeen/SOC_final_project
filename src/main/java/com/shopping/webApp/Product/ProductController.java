package com.shopping.webApp.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService pService;

    @Autowired
    public ProductController(ProductService pService) {
        this.pService = pService;
    }

    @GetMapping("/listProduct")
    public String viewHomePage(Model model) {
        List<Product> listProducts = pService.getProducts();
        model.addAttribute("listProduct", listProducts);

        return "listProduct";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        return "index";
    }

    @GetMapping("/products")
    public String productPage(Model model) {
        return "products";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @GetMapping("/listProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        pService.deleteProduct(id);
        return "redirect:/listProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product p) {
        pService.addNewProduct(p);
        return "redirect:/listProduct";
    }

}

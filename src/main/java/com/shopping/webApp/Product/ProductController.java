package com.shopping.webApp.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService pService;

    @Autowired
    public ProductController(ProductService pService) {
        this.pService = pService;
    }

    @GetMapping("/home")
    public String viewHomePage(Model model) {
        List<Product> listProducts = pService.getProducts();
        model.addAttribute("listProduct", listProducts);

        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@ModelAttribute("product") Long pId) {
        pService.deleteProduct(pId);
        return "redirect:/";
    }

    @PostMapping("/updateProduct")
    public String saveProduct(@ModelAttribute("product") Product p) {
        pService.addNewProduct(p);
        return "redirect:/";
    }

}

package com.shopping.webApp.cartItem;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Customer.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartItemController {

    private final CartItemService cartService;

    private final CustomerService customerService;

    public CartItemController(CartItemService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }


    @GetMapping("/shoppingCart")
    public String showShoppingCart(Model model){

        Customer customer = customerService.getCustomerById(1L);

        List<CartItem> cartItems = cartService.listCartItems(customer);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "ShoppingCart");

        return "shoppingCart";
    }

}

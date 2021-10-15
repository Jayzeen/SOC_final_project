package com.shopping.webApp.cartItem;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestController {

    @Autowired
    private CartItemService cartService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Long productId, @PathVariable("qty") Integer quantity) {
//        Added a hardcoded customer for testing purposes
        Customer customer = customerService.getCustomerById(1L);

        Integer addedQuantity = cartService.addProduct(productId, quantity, customer);

        return addedQuantity + " Item(s) of this product were added to your shopping cart.";

    }

    @PostMapping("/cart/update/{pid}/{qty}")
    public String updateQuantity(@PathVariable("pid") Long productId, @PathVariable("qty") Integer quantity) {
//        Added a hardcoded customer for testing purposes
        Customer customer = customerService.getCustomerById(1L);

        float subTotal = cartService.updateQuantity(productId, quantity, customer);

        return String.valueOf(subTotal);

    }

    @PostMapping("/cart/delete/{pid}")
    public String removeProductFromCart(@PathVariable("pid") Long productId) {
//        Added a hardcoded customer for testing purposes
        Customer customer = customerService.getCustomerById(1L);

        cartService.removeProduct(productId, customer);

        return "The product has been removed from your shopping cart";

    }

}

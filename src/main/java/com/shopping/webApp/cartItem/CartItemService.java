package com.shopping.webApp.cartItem;

import com.shopping.webApp.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartRepo;

    public CartItemService(CartItemRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public List<CartItem> listCartItems(Customer customer){
        return cartRepo.findByCustomer(customer);
    }

}

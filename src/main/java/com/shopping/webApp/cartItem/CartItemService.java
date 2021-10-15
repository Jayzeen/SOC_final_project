package com.shopping.webApp.cartItem;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Product.Product;
import com.shopping.webApp.Product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartItemService {

    private final CartItemRepository cartRepo;
    private final ProductRepository prodRepo;

    public CartItemService(CartItemRepository cartRepo, ProductRepository prodRepo) {
        this.cartRepo = cartRepo;
        this.prodRepo = prodRepo;
    }

    public List<CartItem> listCartItems(Customer customer){
        return cartRepo.findByCustomer(customer);
    }

    //Adding product to shopping cart by add to cart button
    public Integer addProduct(Long productId, Integer quantity, Customer customer){
        Integer addedQuantity = quantity;

        Product product = prodRepo.findById(productId).get();

        CartItem cartItem = cartRepo.findByCustomerAndProduct(customer, product);

        if (cartItem != null) {
            addedQuantity = cartItem.getAmount() + quantity;
            cartItem.setAmount(addedQuantity);
        }
        else {
            cartItem = new CartItem();
            cartItem.setAmount(quantity);
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
        }
        cartRepo.save(cartItem);

        return addedQuantity;
    }

    //Updating cart item quantity in shopping cart
    public float updateQuantity(Long productId, Integer quantity, Customer customer) {
        cartRepo.updateQuantity(quantity,productId, customer.getCId());

        Product product = prodRepo.findById(productId).get();
        float subTotal = (float) (product.getPrice() * quantity);

        return subTotal;
    }

    //Delete cart item in shopping cart
    public void removeProduct(Long productId, Customer customer) {
        cartRepo.deleteByCustomerAndProduct(customer.getCId(), productId);
    }

}

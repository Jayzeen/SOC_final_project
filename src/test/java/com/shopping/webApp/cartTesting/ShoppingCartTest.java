package com.shopping.webApp.cartTesting;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Product.Product;
import com.shopping.webApp.cartItem.CartItem;
import com.shopping.webApp.cartItem.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTest {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem() {
        Product product = entityManager.find(Product.class, 25L);
        Customer customer = entityManager.find(Customer.class, 2L);

        CartItem newItem = new CartItem();
        newItem.setCustomer(customer);
        newItem.setProduct(product);
        newItem.setAmount(5);

        CartItem savedCartItem = cartRepo.save(newItem);

        assert(savedCartItem.getId() > 0);

    }

    @Test
    public void testGetCartItemsByCustomer() {
        Customer customer = new Customer();
        customer.setCId(1L);

        List<CartItem> cartItems = cartRepo.findByCustomer(customer);

        //Customer with ID 1 had 2 cart items. Therefore, we will assert for cart size is equal to 2
        assert(cartItems.size() == 2);

    }

}

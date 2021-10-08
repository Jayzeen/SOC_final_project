package com.shopping.webApp.cartTesting;

import com.shopping.webApp.Product.Product;
import com.shopping.webApp.cartItem.CartItem;
import com.shopping.webApp.cartItem.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class ShoppingCartTest {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem() {
        Product product = entityManager.find(Product.class, 25L);

        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setAmount(1);

        CartItem savedCartItem = cartRepo.save(newItem);

        assert(savedCartItem.getId() > 0);

    }

}

package com.shopping.webApp.cartItem;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCustomer(Customer customer);

    //Finding cartitem by productid and customer
    CartItem findByCustomerAndProduct(Customer customer, Product product);

    //Updating quantity of cart items in the shopping cart
    @Query("UPDATE CartItem c SET c.amount = ?1 WHERE c.product.pId = ?2 AND c.customer.cId = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Long productId, Long customerId);

    //Removing cart item
    @Query("DELETE FROM CartItem c WHERE c.customer.cId = ?1 AND c.product.pId = ?2")
    @Modifying
    void deleteByCustomerAndProduct(Long customerId, Long productId);

}

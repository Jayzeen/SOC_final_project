package com.shopping.webApp.cartItem;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Product.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cId")
    private Customer customer;

    private int amount;

//    Calculating subtotal of products in shopping cart
    @Transient
    public float getSubTotal() {
        return (float) (this.product.getPrice() * amount);
    }


}

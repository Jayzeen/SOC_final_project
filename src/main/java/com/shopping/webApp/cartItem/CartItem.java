package com.shopping.webApp.cartItem;

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

    private String cEmail = "1234@gmail.com";

    private int amount;


}

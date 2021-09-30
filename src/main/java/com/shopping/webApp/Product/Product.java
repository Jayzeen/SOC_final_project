package com.shopping.webApp.Product;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product implements Serializable {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long pId;

    private String pName;
    private double price;
    private int amount;
    private String category;
    private String pImage;

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    private boolean hasStock = true;
}

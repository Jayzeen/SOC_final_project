package com.shopping.webApp.Customer;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Customer {

    @Id
    private Long cId;

    private String cName;
    private String cEmail;


}

//This class is for testing purposes only
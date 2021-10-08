package com.shopping.webApp.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository custRepo;

    @Autowired
    public CustomerService(CustomerRepository custRepo) {
        this.custRepo = custRepo;
    }

    public Customer getCustomerById(Long id){
        return custRepo.findById(id).get();
    }

}

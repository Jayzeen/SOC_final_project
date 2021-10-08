package com.shopping.webApp.CustomerTesting;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CustomerTest {

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addOneCustomer(){
        Customer newCustomer = new Customer();

        newCustomer.setCId(2L);
        newCustomer.setCName("smith");
        newCustomer.setCEmail("smith@gmail.com");

        Customer savedCustomer = custRepo.save(newCustomer);

        assert(savedCustomer.getCId() > 0);

    }

}

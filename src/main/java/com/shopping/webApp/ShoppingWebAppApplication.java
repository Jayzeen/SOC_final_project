package com.shopping.webApp;

import com.shopping.webApp.Customer.Customer;
import com.shopping.webApp.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ShoppingWebAppApplication {

	@Autowired
	private static CustomerRepository custRepo;

	public static void main(String[] args) {

		SpringApplication.run(ShoppingWebAppApplication.class, args);
		Customer newCustomer = new Customer();

		newCustomer.setCId(3L);
		newCustomer.setCName("angel");
		newCustomer.setCEmail("angel@gmail.com");

		Customer savedCustomer = custRepo.save(newCustomer);
	}

}

package com.shopping.webApp.productTesting;

import com.shopping.webApp.Product.Product;
import com.shopping.webApp.Product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class ProductTest {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addOneProductItem() throws IOException {

        Product newProduct = new Product();

        InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("1.jpg");

        MockMultipartFile img = new MockMultipartFile("1,jpg", "1.jpg", "image/jpg", file);

        String folderPath = "src/main/resources/static/assets/pImages/";
        byte[] bytes = img.getBytes();
        Path path = Paths.get(folderPath + img.getOriginalFilename());
        Files.write(path, bytes);

        newProduct.setpImage(img.getOriginalFilename());
        newProduct.setCategory("laptops");
        newProduct.setPrice(200.00);
        newProduct.setPName("Acer 1234 laptop");
        newProduct.setAmount(50);

        Product savedProduct = productRepo.save(newProduct);

        assert(savedProduct.getPId() > 0);

    }

}

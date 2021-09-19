package com.shopping.webApp.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository pRepository;

    @Autowired
    public ProductService(ProductRepository pRepository) {
        this.pRepository = pRepository;
    }

    public List<Product> getProducts() {
        return pRepository.findAll();
    }

    public void addNewProduct(Product product, MultipartFile image) throws IOException {

        //Optional<Product> productOptional = pRepository.findProductById(product.getPId());
        String folderPath = "src/main/resources/static/assets/pImages/";
        byte[] bytes = image.getBytes();
        Path path = Paths.get(folderPath + image.getOriginalFilename());
        Files.write(path, bytes);

       product.setpImage(image.getOriginalFilename());

        pRepository.save(product);

    }

    public void deleteProduct(Long pId){
        boolean exists = pRepository.existsById(pId);
        if (!exists) {
            throw new IllegalStateException("Product with ID - " + pId + " does not exist");
        }
        pRepository.deleteById(pId);
    }

    public void updateProduct(Long pId, String pName, int amount, double price){
        Product product = pRepository.findById(pId).orElseThrow(
                () -> new IllegalStateException("Product with ID - " + pId + " does not exists")
        );

        //Checking if name is not null and also not same
        if( pName != null && !Objects.equals(product.getPName(), pName)) {
            product.setPName(pName);
        }

        if( amount > 0 ){
            product.setAmount(amount);
        }

        if ( price > 0){
            product.setPrice(price);
        }

    }

}

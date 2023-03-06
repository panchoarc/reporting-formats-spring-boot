package com.reportes.reportesservice;

import com.reportes.reportesservice.entity.Product;
import com.reportes.reportesservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReportesServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReportesServiceApplication.class, args);
    }

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {

        productRepository.deleteAll();


        Product product1 = new Product();
        product1.setName("Pro1");
        product1.setPrice(Double.valueOf("12.25"));

        Product product2 = new Product();
        product2.setName("Pro2");
        product2.setPrice(Double.valueOf("25"));

        Product product3 = new Product();
        product3.setName("Pro3");
        product3.setPrice(Double.valueOf("37"));

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        productRepository.saveAll(productList);

    }
}

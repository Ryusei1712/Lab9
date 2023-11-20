package com.example.lab09;

import com.example.lab09.model.Product;
import com.example.lab09.service.OrderService;
import com.example.lab09.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;


@SpringBootApplication
public class Lab09Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab09Application.class, args);
    }

}

package com.example.lab09.service;

import com.example.lab09.model.Product;
import com.example.lab09.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }


    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public void updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setCode(updatedProduct.getCode());
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setIllustration(updatedProduct.getIllustration());
            existingProduct.setDescription(updatedProduct.getDescription());

            // Lưu lại sản phẩm đã cập nhật vào cơ sở dữ liệu
            productRepository.save(existingProduct);
        }
        // Nếu sản phẩm không tồn tại, bạn có thể xử lý theo ý muốn (ví dụ, throw exception hoặc trả về giá trị khác)
    }

}

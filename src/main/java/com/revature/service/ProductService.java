package com.revature.service;

import com.revature.exception.ResourceNotCreatedException;
import com.revature.exception.ResourceNotFoundException;
import com.revature.model.Product;
import com.revature.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        return productRepository.create(product).orElseThrow(() -> new ResourceNotCreatedException(Product.class.getSimpleName()));
    }

    public Product getProductById(Integer id){
        return productRepository.getById(id).orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), id));
    }

    public List<Product> getAllProducts(){
        return productRepository.getAll();
    }

    public Product updateProductById(Product product, Integer id){
        return productRepository.updateById(product, id).orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), id));
    }

    public Product deleteProductById(Integer id){
        return productRepository.deleteById(id).orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), id));
    }
}

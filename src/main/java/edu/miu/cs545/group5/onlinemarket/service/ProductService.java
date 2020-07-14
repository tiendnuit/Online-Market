package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    public Product saveProduct(Product product);
    public Page<Product> getPaginatedProduct(Pageable pageable);
    public Product findProductById(Long id);
    public void deleteProductById(Long id);
    public Page<Product> findBySellerId(Pageable pageable,Long id);
}

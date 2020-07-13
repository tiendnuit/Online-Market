package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    public Product saveProduct(Product product);
    public Page<Product> getPaginatedProduct(Pageable pageable);
    public Product findProductById(Long id);
}

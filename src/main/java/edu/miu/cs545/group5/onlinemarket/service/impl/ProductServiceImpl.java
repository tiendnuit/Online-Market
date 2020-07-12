package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.repository.ProductRepository;
import edu.miu.cs545.group5.onlinemarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}

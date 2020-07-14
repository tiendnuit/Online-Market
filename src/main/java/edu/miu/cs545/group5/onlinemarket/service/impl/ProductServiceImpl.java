package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.ProductRepository;
import edu.miu.cs545.group5.onlinemarket.service.ProductService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        Optional<User> currentUser = userService.getLoggedUser();
        if (currentUser.isPresent() && currentUser.get().getRole().equals(Constants.ROLE_BUYER)){
            Buyer buyer = (Buyer) currentUser.get();
            for (Product p : products) {
                if (buyer.getFollowings().contains(p.getSeller())) {
                    p.getSeller().setFollowingByCurrentUser(true);
                } else {
                    p.getSeller().setFollowingByCurrentUser(false);
                }
            }
        }

        return products;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getPaginatedProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findBySellerId(Pageable pageable, Long id) {
        return productRepository.findBySellerId(pageable,id);
    }

}

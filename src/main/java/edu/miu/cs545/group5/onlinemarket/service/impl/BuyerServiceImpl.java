package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.*;
import edu.miu.cs545.group5.onlinemarket.repository.BuyerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.ProductRepository;
import edu.miu.cs545.group5.onlinemarket.repository.ProductReviewRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import edu.miu.cs545.group5.onlinemarket.service.BuyerService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    UserService userService;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    ProductReviewRepository productReviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void unfollowSeller(Long id) {
        Buyer currentUser = (Buyer)userService.getLoggedUser().get();
        List<Seller> sellers = currentUser.getFollowings();
        int index = -1;
        for (Seller seller : sellers) {
            index += 1;
            if (seller.getId() == id) break;
        }

        if (index >= 0) sellers.remove(index);

        currentUser.setFollowings(sellers);
        userService.save(currentUser);
    }

    @Override
    public void followSeller(Long id) {
        Buyer currentUser = (Buyer)userService.getLoggedUser().get();
        Seller seller = (Seller)userService.getById(id).get();
        List<Seller> sellers = currentUser.getFollowings();
        sellers.add(seller);
        currentUser.setFollowings(sellers);
        userService.save(currentUser);

    }

    public List<Buyer> findAllBuyers(){
        return  buyerRepository.findAllBuyers();
    }

    @Override
    public void reviewProduct(Long id, String message) {
        Buyer currentUser = (Buyer)userService.getLoggedUser().get();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductReview review = new ProductReview();
            review.setBuyer(currentUser);
            review.setProduct(product.get());
            review.setMessage(message);
            productReviewRepository.save(review);
        }
    }

}

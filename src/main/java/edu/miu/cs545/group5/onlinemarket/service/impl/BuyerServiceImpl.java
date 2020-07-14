package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.BuyerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import edu.miu.cs545.group5.onlinemarket.service.BuyerService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    UserService userService;

    @Autowired
    BuyerRepository buyerRepository;

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

}

package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.BuyerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.SellerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public void update(User user) {

    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<? extends User> getAllApprovedUsers(){

        List allApprovedUsers = new ArrayList<>();
        List<Seller> approvedSellers = sellerRepository.findSellersByApprovedTrue();
        List<Buyer> approvedBuyers = buyerRepository.findBuyersByApprovedTrue();

        allApprovedUsers.add(approvedBuyers);
        allApprovedUsers.add(approvedSellers);

        return allApprovedUsers;

    }

    @Override
    public List<? extends User> getNotApprovedUsers() {
        List NotApprovedUsers = new ArrayList<>();
        List<Seller> notApprovedSellers = sellerRepository.findSellersByApprovedFalse();
        List<Buyer> notApprovedBuyers = buyerRepository.findBuyersByApprovedFalse();

        NotApprovedUsers.add(notApprovedSellers);
        NotApprovedUsers.add(notApprovedBuyers);
        return NotApprovedUsers;
    }


}

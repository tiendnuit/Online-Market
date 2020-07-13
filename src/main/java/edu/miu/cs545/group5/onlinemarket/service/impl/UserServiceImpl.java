package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.BuyerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.SellerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void approveBuyer(Long id) {
        //not implemented
    }

    @Override
    public List<User> findAllUserSellersAndBuyers() {
        return userRepository.findAllUserSellersAndBuyers();
    }




    public void enableUser(Long id){
            User user = userRepository.findById(id).get();

            if(user.getActive()==1){
                user.setActive(0);
            }
            else {
                user.setActive(1);
            }
        Seller seller = (Seller) user;
        seller.setApproved(true);
        userRepository.save(seller);

    }

    @Override
    public List<Seller> findAllSellers() {
        return userRepository.findAllSellers();
    }


    @Override
    public void disAbleUser(Long id) {

    }
    @Override
    public void approveSeller(Long id) {
        User user = userRepository.findById(id).get();
        if(user.getRole().equals("SELLER")){
          Seller  seller = (Seller) user;
            if(!seller.isApproved()){
                seller.setApproved(true); }
            else{ seller.setApproved(false); }
            userRepository.save(seller);
        }
    }


    @Override
    public List<? extends User> getNotApprovedUsers() {
        return sellerRepository.findSellersByApprovedFalse();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Seller> getAllSellers() {
        return userRepository.findAllSellers();
    }


    @Override
    public Optional<User> getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        return userRepository.findByEmail(email);
    }




}

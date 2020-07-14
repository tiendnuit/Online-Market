package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.BuyerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.SellerRepository;
import edu.miu.cs545.group5.onlinemarket.repository.UserRepository;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void create(User user) {
        User newUser = user;
        if (user.getRole().equals(Constants.ROLE_SELLER)) {
            newUser = new Seller(user.getFirstName(), user.getLastName(),
                    user.getEmail(), user.getPhone(),
                    user.getBirthDate(), user.getPassword(),
                    user.getAddress(), user.getRole(),
                    user.getActive(), false);
        } else if (user.getRole().equals(Constants.ROLE_BUYER)) {
            newUser = new Buyer(user.getFirstName(), user.getLastName(),
                    user.getEmail(), user.getPhone(),
                    user.getBirthDate(), user.getPassword(),
                    user.getAddress(), user.getRole(),
                    user.getActive(), 0, new ArrayList<>());
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setActive(1);
        userRepository.save(newUser);
    }
    @Override
    public List<User> findAllUserSellersAndBuyers() {
        return userRepository.findAllUserSellersAndBuyers();
    }

    @Override
    public List<Seller> findAllSellers() {
        return userRepository.findAllSellers();
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
    public void activateSeller(Long id) {
        User user = userRepository.findById(id).get();
        Seller  seller = (Seller) user;
        if(user.getActive()==1){
            seller.setActive(0);
        }
            else{
                seller.setActive(1);
            }
            userRepository.save(seller);
        }

    @Override
    public void activateBuyer(Long id) {
        User user = userRepository.findById(id).get();
        Buyer  buyer = (Buyer) user;
        if(user.getActive()==1){
            buyer.setActive(0);
        }
        else{
            buyer.setActive(1);
        }
        userRepository.save(buyer);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            String email = ((UserDetails) principal).getUsername();
            return userRepository.findByEmail(email);
        }
        return Optional.empty();
    }
}

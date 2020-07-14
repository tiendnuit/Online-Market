package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(long id);
    Optional<User> getByEmail(String email);

    void save(User user);
    void create(User user);
    List<User> findAllUserSellersAndBuyers();
    public List<Seller> findAllSellers();
    void delete(String id);
    List<Seller> getAllSellers();
    Optional<User> getLoggedUser();
    public void approveSeller(Long id);
    public void activateSeller(Long id);
    public void activateBuyer(Long id);


}

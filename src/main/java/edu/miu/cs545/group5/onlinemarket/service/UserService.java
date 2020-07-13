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
    void update(User user);
    void save(User user);
    public void approveBuyer(Long id);
    List<User> findAllUserSellersAndBuyers();
    public void enableUser(Long id);
    public List<Seller> findAllSellers();
    public void disAbleUser(Long id);
    List<? extends User> getNotApprovedUsers();
    void delete(String id);
    List<Seller> getAllSellers();
    Optional<User> getLoggedUser();

    public void approveSeller(Long id);


}

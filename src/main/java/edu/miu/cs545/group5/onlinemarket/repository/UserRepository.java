package edu.miu.cs545.group5.onlinemarket.repository;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findById(Long id);
    @Query("select u from User u where u.role <> 'ADMIN' ")
    List<User> findAllUserSellersAndBuyers();
    @Query("select a from User a where  a.role = 'SELLER'")
    public List<Seller> findAllSellers();
}
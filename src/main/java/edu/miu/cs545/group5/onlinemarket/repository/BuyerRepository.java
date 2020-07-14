package edu.miu.cs545.group5.onlinemarket.repository;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    public List<Buyer> findBuyersByApprovedFalse();
    public List<Buyer> findBuyersByApprovedTrue();


    @Query("select buyer from User buyer where buyer.role = 'BUYER'")
    public List<Buyer> findAllBuyers();

    public Optional<Buyer> findById(Long id);
}

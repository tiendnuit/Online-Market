package edu.miu.cs545.group5.onlinemarket.repository;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

      public List<Seller> findAllByApprovedFalse();
      public List<Seller> findAllByApprovedTrue();





}

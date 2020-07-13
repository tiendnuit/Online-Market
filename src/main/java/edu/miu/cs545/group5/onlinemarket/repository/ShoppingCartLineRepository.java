package edu.miu.cs545.group5.onlinemarket.repository;

import edu.miu.cs545.group5.onlinemarket.domain.ShoppingCartLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartLineRepository extends JpaRepository<ShoppingCartLine, Long> {

}

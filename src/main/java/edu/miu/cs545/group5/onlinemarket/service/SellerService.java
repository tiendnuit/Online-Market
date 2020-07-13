package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.dto.SellerResponse;

import java.util.List;
import java.util.Optional;
import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface SellerService {

    List<SellerResponse> getAllSellers();
    public Optional<Seller> getSellerById(Long id);
    public Seller findById(Long id);
    public List<Seller> findAllSeller();
    public void approveSeller(Long id);


}

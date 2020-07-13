package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.dto.SellerMapper;
import edu.miu.cs545.group5.onlinemarket.domain.dto.SellerResponse;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.SellerRepository;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public List<SellerResponse> getAllSellers() {
        List<Seller> allSellersList = sellerRepository.findAll();
        return allSellersList.stream().map(SellerMapper::mapToSellerResponse).collect(Collectors.toList());
    }

//    @Override
//    public List<SellerResponse> getAllSellersNotApproved() {
//        List<Seller> unapprovedSellerList = sellerRepository.findSellersByApprovedFalse();
//            return unapprovedSellerList.stream().map(SellerMapper::mapToSellerResponse).collect(Collectors.toList());
//    }
//    @Override
//    public List<SellerResponse> getAllApprovedSellers(){
//        List<Seller> approvedSellersList = sellerRepository.findAllByApprovedTrue();
//        return approvedSellersList.stream().map(SellerMapper::mapToSellerResponse).collect(Collectors.toList());
//    }

    @Override
    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    public void saveSeller(Seller seller){
        sellerRepository.save(seller);
        
    }

    @Override
    public Seller findById(Long id) {
        return sellerRepository.getOne(id);
    }
}

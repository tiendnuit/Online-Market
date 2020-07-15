package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.ProductReview;
import edu.miu.cs545.group5.onlinemarket.repository.ProductReviewRepository;
import edu.miu.cs545.group5.onlinemarket.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    ProductReviewRepository productReviewRepository;

    @Override
    public List<ProductReview> getNotApprovedReviews() {
        return productReviewRepository.findProductReviewsByApproveFalse();
    }

    @Override
    public void approveReview(Long id) {
        Optional<ProductReview> review = productReviewRepository.findById(id);
        if (review.isPresent()) {
            review.get().setApprove(true);
            productReviewRepository.save(review.get());
        }
    }


}

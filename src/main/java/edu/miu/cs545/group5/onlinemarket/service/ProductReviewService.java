package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.ProductReview;

import java.util.List;

public interface ProductReviewService {
    List<ProductReview> getNotApprovedReviews();
    void approveReview(Long id);
}

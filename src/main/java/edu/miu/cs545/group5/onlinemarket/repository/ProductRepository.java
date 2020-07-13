package edu.miu.cs545.group5.onlinemarket.repository;

import edu.miu.cs545.group5.onlinemarket.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Page<Product> findBySellerId(Pageable pageable,Long id);
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET p.category = :category, p.id = :productId, p.name = :name, p.price = :price," +
            "p.description = :description," +
            "p.fileType = :fileType, p.imageName = :imageName, p.data = :data , p.seller = :seller, p.stock = :stock ," +
            "p.reviews = :review WHERE p.id = :productId")
    int updateAddress(@Param("category") Category category, @Param("productId") Long productId, @Param("name")String name,
                      @Param("price")Double price, @Param("description")String description, @Param("fileType")String fileType,
                      @Param("imageName")String imageName, @Param("data")byte[] data, @Param("seller")Seller seller,
                      @Param("stock")Integer stock, @Param("review") List<ProductReview> reviews);
}

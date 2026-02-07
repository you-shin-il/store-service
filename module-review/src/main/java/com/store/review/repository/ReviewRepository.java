package com.store.review.repository;

import com.store.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductId(Long productId);

    List<Review> findByBuyerId(Long buyerId);

    boolean existsByBuyerIdAndOrderId(Long buyerId, Long orderId);
}

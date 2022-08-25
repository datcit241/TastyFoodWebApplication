package com.tastyfoodwebapplication.repositories;

import com.tastyfoodwebapplication.models.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, String> {
}

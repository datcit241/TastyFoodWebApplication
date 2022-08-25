package com.tastyfoodwebapplication.repositories;

import com.tastyfoodwebapplication.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, String> {

}

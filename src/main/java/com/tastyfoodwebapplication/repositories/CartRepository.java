package com.tastyfoodwebapplication.repositories;

import com.tastyfoodwebapplication.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
}

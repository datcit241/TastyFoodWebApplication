package com.tastyfoodwebapplication.utilities;

import com.tastyfoodwebapplication.models.CustomerOrder;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class OrderComparator {
    public Comparator<CustomerOrder> getDefaultComparator() {
        return (o1, o2) -> {
            int value = o1.getStatus().compareTo(o2.getStatus());

            if (value == 0) {
                return o2.getOrderedAt().compareTo(o1.getOrderedAt());
            }

            return value;
        };
    }

    public Comparator<CustomerOrder> getOrderByRecentnessComparator() {
        return (o1, o2) -> o2.getOrderedAt().compareTo(o1.getOrderedAt());
    }
}

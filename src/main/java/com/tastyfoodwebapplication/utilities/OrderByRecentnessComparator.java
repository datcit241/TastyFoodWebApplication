package com.tastyfoodwebapplication.utilities;

import com.tastyfoodwebapplication.models.Order;

import java.util.*;

public class OrderByRecentnessComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o2.getOrderedAt().compareTo(o1.getOrderedAt());
    }
}

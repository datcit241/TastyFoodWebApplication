package com.tastyfoodwebapplication.utilities;

import com.tastyfoodwebapplication.models.CustomerOrder;

import java.util.*;

public class OrderByRecentnessComparator implements Comparator<CustomerOrder> {
    @Override
    public int compare(CustomerOrder o1, CustomerOrder o2) {
        return o2.getOrderedAt().compareTo(o1.getOrderedAt());
    }
}

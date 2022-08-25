package com.tastyfoodwebapplication.utilities;

import com.tastyfoodwebapplication.models.CustomerOrder;

import java.util.*;

public class DefaultOrderComparator implements Comparator<CustomerOrder> {
    @Override
    public int compare(CustomerOrder o1, CustomerOrder o2) {
        int value = o1.getStatus().compareTo(o2.getStatus());

        if (value == 0) {
            return o2.getOrderedAt().compareTo(o1.getOrderedAt());
        }

        return value;
    }
}

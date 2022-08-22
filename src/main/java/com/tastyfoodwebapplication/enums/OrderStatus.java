package com.tastyfoodwebapplication.enums;

import com.tastyfoodwebapplication.services.OrderService;

public enum OrderStatus {
    Cancelled,
    Pending,
    Preparing,
    Delivering,
    Delivered;

    public boolean isInProgress() {
        return this != Cancelled && this != Delivered;
    }

    public static OrderStatus next(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case Cancelled, Delivered -> orderStatus;
            case Pending -> Preparing;
            case Preparing -> Delivering;
            case Delivering -> Delivered;
        };
    }

}

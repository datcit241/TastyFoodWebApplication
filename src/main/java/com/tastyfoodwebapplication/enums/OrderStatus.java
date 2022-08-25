package com.tastyfoodwebapplication.enums;

public enum OrderStatus {
    Pending,
    Preparing,
    Delivering,
    Cancelled,
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

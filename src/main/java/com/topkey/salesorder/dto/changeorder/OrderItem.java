package com.topkey.salesorder.dto.changeorder;

import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private int quantity;

    // Getters and setters
}
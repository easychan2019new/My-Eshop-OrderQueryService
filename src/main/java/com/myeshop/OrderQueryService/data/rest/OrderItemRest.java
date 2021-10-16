package com.myeshop.OrderQueryService.data.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemRest {

    private String productId;
    private String name;
    private String imageUrl;
    private BigDecimal unitPrice;
    private int quantity;
}

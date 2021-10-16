package com.myeshop.OrderQueryService.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`order`")
public class Order {

    public Order(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "last_updated")
    @JsonIgnore
    private Date lastUpdated;

    @Column(name = "customer_email")
    @JsonIgnore
    private String customerEmail;

    @Column(name = "address_id")
    @JsonIgnore
    private String addressId;

    @Column(name = "payment_id")
    @JsonIgnore
    private String paymentId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonIgnore
    private Set<OrderItem> orderItems = new HashSet<>();

    public void add(OrderItem item) {
        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }

            orderItems.add(item);
            item.setOrder(this);
        }
    }
}

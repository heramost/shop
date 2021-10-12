package com.hera.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static javax.persistence.CascadeType.*;
import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "calculated_price")
    private double calculatedPriceInUsd;

    @OneToMany(mappedBy="order", fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, REFRESH})
    private Set<OrderItem> items;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Basic
    @Temporal(TIMESTAMP)
    private Date timestamp;

    public void addItem(OrderItem orderItem) {
        items = ofNullable(items).orElseGet(HashSet::new);
        items.add(orderItem);
        orderItem.setOrder(this);
    }
}

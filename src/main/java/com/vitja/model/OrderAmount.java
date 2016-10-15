package com.vitja.model;

import javax.persistence.*;

/**
 * Created by Viktor on 15.10.2016.
 */
@Entity
@Table(name = "orderamount")
public class OrderAmount {
    @Id
    @Column(name = "orderAmountId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;
}

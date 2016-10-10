package com.vitja.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Viktor on 09.10.2016.
 */
@Entity
@Table(name = "pricelist")
public class PriceList {
    @Id
    @Column(name = "priceListId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priceList")
    private Set<Order> orders = new HashSet<Order>(0);

    public PriceList() {
    }

    public PriceList(String description) {
        this.description = description;
    }

    public PriceList(String description, Set<Order> orders) {
        this.description = description;
        this.orders = orders;
    }

    public PriceList(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public PriceList(Integer id, String description, Set<Order> orders) {
        this.id = id;
        this.description = description;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PriceList priceList = (PriceList) o;

        return new EqualsBuilder()
                .append(id, priceList.id)
                .append(description, priceList.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(description)
                .toHashCode();
    }
}

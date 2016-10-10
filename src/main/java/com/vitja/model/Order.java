package com.vitja.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by Viktor on 09.10.2016.
 */
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "label")
    private String label;

    @Column(name = "page")
    private Integer page;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "bonusPoint")
    private Integer bonusPoint;

    @Column(name = "salesVolume")
    private Double salesVolume;

    @Column(name = "distributionPrice")
    private Double distributionPrice;

    @Column(name = "purchasingPrice")
    private Double purchasingPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priceListId", nullable = false)
    private PriceList priceList;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistributionPrice() {
        return distributionPrice;
    }

    public void setDistributionPrice(Double distributionPrice) {
        this.distributionPrice = distributionPrice;
    }

    public Double getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(Double purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public Double getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Double salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(Integer bonusPoint) {
        this.bonusPoint = bonusPoint;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new EqualsBuilder()
                .append(id, order.id)
                .append(priceList, order.priceList)
                .append(code, order.code)
                .append(label, order.label)
                .append(page, order.page)
                .append(name, order.name)
                .append(bonusPoint, order.bonusPoint)
                .append(salesVolume, order.salesVolume)
                .append(distributionPrice, order.distributionPrice)
                .append(purchasingPrice, order.purchasingPrice)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(priceList)
                .append(code)
                .append(label)
                .append(page)
                .append(name)
                .append(bonusPoint)
                .append(salesVolume)
                .append(distributionPrice)
                .append(purchasingPrice)
                .toHashCode();
    }
}

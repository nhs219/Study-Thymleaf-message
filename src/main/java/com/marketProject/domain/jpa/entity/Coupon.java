package com.marketProject.domain.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupon extends BaseEntity{
    @Id
    private String couponCode;
    private String name;
    private Long discountPrice;
    private int discountPercent;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }
}

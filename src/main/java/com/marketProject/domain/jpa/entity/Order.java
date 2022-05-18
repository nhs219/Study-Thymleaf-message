package com.marketProject.domain.jpa.entity;

import com.marketProject.domain.member.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class Order {
    private String code;
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "coupon_code")
    private String couponCode;
    private LocalDateTime order_datetime;
    private Integer deliveryPrice;
    private Long totalPrice;
    private OrderStatus orderStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public LocalDateTime getOrder_datetime() {
        return order_datetime;
    }

    public void setOrder_datetime(LocalDateTime order_datetime) {
        this.order_datetime = order_datetime;
    }

    public Integer getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Integer deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

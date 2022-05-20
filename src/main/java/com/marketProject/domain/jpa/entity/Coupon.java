package com.marketProject.domain.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Coupon extends BaseEntity{
    @Id @Column(name = "couponCode")
    private String code;
    private String name;
    private Long discountPrice;
    private int discountPercent;

    @OneToMany(mappedBy = "coupon")
    private List<Order> orders = new ArrayList<>();
}

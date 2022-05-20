package com.marketProject.domain.jpa.entity;

import com.marketProject.domain.member.DeliveryStatus;
import com.marketProject.domain.member.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @Column(name = "order_code")
    private String code;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "coupon_code")
    private Coupon coupon;

    private LocalDateTime orderDatetime;

    private OrderStatus orderStatus;

    @Embedded
    private Address address;

    private Integer deliveryPrice;

    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    /**
     * 연관관계 편의 메서드
     * 연관관계 편의 메서드는 핵심적으로 컨트롤 하는 쪽에서 가지고 있는 것이 좋음.
     * 아래는 일단 예시이고, 프로젝트하면서 하나씩 추가해보기.
     */
    // 연관관계 메서드
    // 연관관계 편의 메서드는 핵심적으로 컨트롤 하는 쪽에서 가지고 있는 것이 좋음
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderDetails(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }
}

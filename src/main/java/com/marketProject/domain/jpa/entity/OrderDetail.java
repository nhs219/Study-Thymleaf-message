package com.marketProject.domain.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderDetail {
    @Id @GeneratedValue
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_code")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product")
    private Product product;

    private Integer quantity;

    private Long price;
}

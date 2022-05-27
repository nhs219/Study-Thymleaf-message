package com.marketProject.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Cart extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_code")
    private Product product;
    private Long memberId;
    private Integer quantity;
}

package com.marketProject.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class ProductCategory {

    @Id @GeneratedValue
    @Column(name = "product_category_id")
    private Long Id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "category_code")
    private Category category;

}

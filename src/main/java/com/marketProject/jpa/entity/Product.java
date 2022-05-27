package com.marketProject.jpa.entity;

import com.marketProject.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Product extends BaseEntity{
    @Id @Column(name = "product_code")
    private String Code;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories = new ArrayList<>();
    @NotEmpty
    private Long price;
    @NotEmpty
    private int stockQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private String description;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();

    //비즈니스 로직

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock.");
        }
        this.stockQuantity = restStock;
    }
}

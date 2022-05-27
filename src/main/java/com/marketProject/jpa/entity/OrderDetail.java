package com.marketProject.jpa.entity;

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

    // 생성 메서드
    public static OrderDetail createOrderDetail(Product product, Long price, int quantity) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setPrice(price); // 할인 적용
        orderDetail.setQuantity(quantity);

        product.removeStock(quantity);
        return orderDetail;
    }

    // 비즈니스 로직
    /**
     * 취소 시 재고 수량 원복
     */
    public void cancel() {
        getProduct().addStock(quantity);
    }

    public Long getTotalPrice() {
        return getPrice() * getQuantity();
    }
}

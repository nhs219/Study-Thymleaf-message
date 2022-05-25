package com.marketProject.service;

import com.marketProject.domain.member.OrderStatus;
import com.marketProject.exception.NotEnoughStockException;
import com.marketProject.jpa.entity.*;
import com.marketProject.jpa.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderServiceImpl orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    void 상품주문() {
        //given
        Member member = makeMember();
        Product product = makeProduct("testProduct", 10000L, 10);

        //when
        int orderQuantity = 2;
        String orderCode = orderService.order(member.getId(), product.getCode(), orderQuantity);

        //then
        Order getOrder = orderRepository.findOne(orderCode);

        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getOrderStatus());
        assertThat(1).isEqualTo(getOrder.getOrderDetails().size());
        assertThat(10000L*orderQuantity).isEqualTo(getOrder.getTotalPrice());
        assertThat(8).isEqualTo(product.getStockQuantity());
    }

    @Test
    void 주문취소() {
        //given
        Member member = makeMember();
        Product product = makeProduct("testProduct", 10000L, 10);

        int orderQuantity = 2;
        String orderCode = orderService.order(member.getId(), product.getCode(), orderQuantity);

        //when
        orderService.cancelOrder(orderCode);

        //then
        Order getOrder = orderRepository.findOne(orderCode);

        assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getOrderStatus());
        assertThat(10).isEqualTo(product.getStockQuantity());
    }

    @Test
    void 상품주문_재고수량초과() {
        //given
        Member member = makeMember();
        Product product = makeProduct("testProduct", 10000L, 10);

        //when
        int orderQuantity = 11;

        //then
        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), product.getCode(), orderQuantity));
    }

    private Member makeMember() {
        Member member = new Member();
        member.setEmail("test@test.com");
        member.setPassword("1234");;
        member.setName("test");
        member.setPhone("01011111111");
        member.setAddress(new Address("singil-ro", "1234", "test"));
        em.persist(member);
        return member;
    }

    private Product makeProduct(String name, Long price, int stockQuantity) {
        Product product = new Product();

        product.setCode("P00001");
        product.setName(name);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        em.persist(product);

        return product;
    }

}
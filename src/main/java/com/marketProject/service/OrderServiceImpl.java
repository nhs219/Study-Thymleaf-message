package com.marketProject.service;

import com.marketProject.jpa.entity.Member;
import com.marketProject.jpa.entity.Order;
import com.marketProject.jpa.entity.OrderDetail;
import com.marketProject.jpa.entity.Product;
import com.marketProject.jpa.repository.MemberRepository;
import com.marketProject.jpa.repository.OrderRepository;
import com.marketProject.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    /**
     * 주문
     */
    @Transactional
    public String order(Long memberId, String productCode, int quantity) {
        // 회원 조회
        Member member = memberRepository.findOne(memberId);
        Product product = productRepository.findOne(productCode);

        // 주문 상품 생성
        OrderDetail orderDetail = OrderDetail.createOrderDetail(product, product.getPrice(), quantity);

        // 주문 생성
        // 한번에 여러 상품 주문하는 로직 추후에 구현 필요
        Order order = Order.createOrder(member, orderDetail);

        // 주문 저장
        orderRepository.save(order);

        return order.getCode();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(String orderCode) {
        // 주문 조회
        Order order = orderRepository.findOne(orderCode);

        //주문 취소
        order.cancel();
    }

    /**
     * 주문 검색 - 추후 개발 예정
     */
//    public List<Order> findOrders(String orderCode) {
//        return null;
//    }
}

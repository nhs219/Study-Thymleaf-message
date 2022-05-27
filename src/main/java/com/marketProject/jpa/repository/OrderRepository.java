package com.marketProject.jpa.repository;

import com.marketProject.jpa.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(String code) {
        return em.find(Order.class, code);
    }

    // 동적 쿼리의 지옥.... jpa criteria도 헬임
    // querydsl 공부가 필요함
    public List<Order> findAll(OrderSearch orderSearch) {
       return em.createQuery("select o from Order o join o.member m" +
                "where o.status = :status" +
                "and m.email like :email", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("email", orderSearch.getMemberEmail())
                .setMaxResults(1000) // 최대 1000건
                .getResultList();
    }
}

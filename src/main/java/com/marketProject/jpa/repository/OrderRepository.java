package com.marketProject.jpa.repository;

import com.marketProject.domain.member.OrderStatus;
import com.marketProject.jpa.entity.Order;
import com.marketProject.jpa.entity.QMember;
import com.marketProject.jpa.entity.QOrder;
import com.marketProject.service.OrderServiceImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;

        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()), emailLike(orderSearch.getMemberEmail()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression emailLike(String memberEmail) {
        if (!StringUtils.hasText(memberEmail)) {
            return null;
        }
        return QMember.member.email.like(memberEmail);
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null) {
            return null;
        }
        return QOrder.order.orderStatus.eq(statusCond);
    }
}

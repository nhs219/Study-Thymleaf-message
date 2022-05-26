package com.marketProject.jpa.repository;

import com.marketProject.jpa.entity.MemberSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberSvcRepository {

    private final EntityManager em;

    public void save(MemberSvc memberSvc) {
        em.persist(memberSvc);
    }
}

package com.marketProject.jpa.repository;

import com.marketProject.jpa.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;

    public Member save(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화 해서 insert
        em.persist(member);
        return member;
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m " +
                                        "join m.memberSvc ms " +
                                        "where ms.deleteDatetime is null", Member.class)
                .getResultList();
    }

    public List<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }
}

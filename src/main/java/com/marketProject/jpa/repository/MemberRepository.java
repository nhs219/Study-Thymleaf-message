package com.marketProject.jpa.repository;

import com.marketProject.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m " +
            "inner join MemberSvc ms " +
            "on m.id = ms.member.id " +
            "where m.email =: email " +
            "and ms.deleteDatetime is null")
    Optional<Member> findByEmail(String email);


//    public Member save(Member member, String password) {
//        member.setPassword(password);
//        member.setRoles(Collections.singletonList("ROLE_USER"));
//        em.persist(member);
//        return member;
//    }
//
//    public Member findOne(Long id) {
//        return em.find(Member.class, id);
//    }
//
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m " +
//                                        "join m.memberSvc ms " +
//                                        "where ms.deleteDatetime is null", Member.class)
//                .getResultList();
//    }
//
//    public List<Member> findByEmail(String email) {
//        return em.createQuery("select m from Member m where m.email = :email", Member.class)
//                .setParameter("email", email)
//                .getResultList();
//    }
}

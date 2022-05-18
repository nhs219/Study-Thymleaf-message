package com.marketProject.domain.member;

import com.marketProject.domain.jpa.entity.Member;
import com.marketProject.domain.jpa.entity.MemberSvc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Override
    public ResponseEntity<String> login(Login login) {

        String sessionUUID = UUID.randomUUID().toString();

        log.info("sessionUUID : {} ", sessionUUID );

        return null;
    }

    @Override
    public ResponseEntity<String> join(MemberDTO memberVo) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //MEMEBER 테이블에 insert
        Member member = new Member();
        member.setPassword(memberVo.getPassword());
        member.setEmail(memberVo.getEmail());
        member.setAddress(memberVo.getAddress());
        member.setPhone(memberVo.getPhone());
        member.setGrade(Grade.FAMILY);

        //등록
        em.persist(member);

        transaction.commit();

        em.close();
        emf.close();

        ResponseEntity response = new ResponseEntity(HttpStatus.OK);
        log.info("response : {}", response);

        return response;
    }
}

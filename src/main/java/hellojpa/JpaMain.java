package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory 를 1개 생성한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트렌젝션 단위 처리마다 EntityManager가 1개씩 필요하다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 멤버 저장
        Member member = new Member();
        member.setId(2L);
        member.setName("helloB");
        em.persist(member);

        tx.commit();

        em.close();

        emf.close();
    }
}

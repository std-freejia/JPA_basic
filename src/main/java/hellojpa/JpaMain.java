package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory 를 1개 생성한다. 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트렌젝션 단위 처리마다 EntityManager가 1개씩 필요하다. 고객의 요청 한 개에 대해 동작
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member findMember = em.find(Member.class, 150L);
            findMember.setName("hello");

            // 특정 객체만 준영속 상태로 변경
            em.detach(findMember); // findMember 에 관련된 모든 변경사항이 영속성 컨텍스트에서 삭제된다. JPA가 관리 안함.
            // 영속성 컨텍스트(1차 캐시)를 모두 초기화
            em.clear();

            System.out.println("==========");

            tx.commit(); // DB에 insert SQL 실행

        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

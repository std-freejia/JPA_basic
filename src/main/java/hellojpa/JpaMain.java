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

            tx.commit(); // DB에 insert SQL 실행
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

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

        try{
            // em : 컬렉션이라고 여겨도 된다.

            // find() 클래스의 식별자를 주면, 조회
            Member findMember = em.find(Member.class, 2L);

            // 객체의 setter를 사용하기만 했는데 수정 !!
            findMember.setName("HelloJPA");
            tx.commit();

        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

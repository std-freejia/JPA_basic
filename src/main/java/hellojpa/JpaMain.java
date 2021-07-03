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

            /** 현재 Team과 Member 는 양방향 연관관계가 설정되어 있고, 연관관계의 주인은 Member엔티티다.
             *
             * 1. 연관관계의 주인이 아닌, Team 을 먼저 생성하여 persist()
             * 2. 연관관계의 주인 Member에 Team을 set하자.
             * */
            Team team = new Team();
            team.setName("teamA");
            // team.getMembers().add(member);
            em.persist(team); // 팀 저장

            Member member = new Member();
            member.setName("jpamember");
            member.setTeam(team); // 팀을 지정
            em.persist(member);

            /** 연관관계의 주인인 Member 엔티티의 team에는, team_id 가 null 이 되는 문제 발생!
             * 왜냐하면 team.getMembers().add(member); 가 불가능한 코드이기 때문이다.
             * team.getMembers() 는 읽기 전용 칼럼이다. add할 수 없다.
             * mappedBy 된 칼럼은 읽기 전용이기 때문이다.
             * */

            em.flush(); /** 쓰기지연 SQL 저장소에 있던 쿼리들을 전부 실행한다. (변경 감지된 내용들을 전부 DB에 쿼리 실행하여 반영) */
            em.clear(); /** 영속성 컨텍스트를 초기화 한다. */


            tx.commit(); // DB에 insert SQL 실행
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

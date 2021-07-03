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

            /** [양방향 연관관계 주의점]  항상 Team, Member 양쪽에 값을 넣자. [연관관계 메소드 구현하 ]  */

            Member member = new Member();
            member.setName("jpamember");
            //member.changeTeam(team); // 연관관계 편의 메소드
            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.addMember(member); // 연관관계 편의 메소드
            em.persist(team); // 팀 저장


            em.flush(); // 쓰기지연 SQL 저장소에 있던 쿼리들을 전부 실행한다. (변경 감지된 내용들을 전부 DB에 쿼리 실행하여 반영)
            em.clear(); // 영속성 컨텍스트를 초기화 한다.

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시. 영속성 컨텍스트에서 가져온것.
            List<Member> members = findTeam.getMembers();

            System.out.println("==========");
            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }
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

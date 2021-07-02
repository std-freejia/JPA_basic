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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team); // 팀 저장

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB); // 팀 저장

            Member member = new Member(); // 멤버 하나 생성
            member.setName("member1");
            member.setTeam(team);  // 팀객체를 바로 세팅한다.
            em.persist(member);

            /** Member 엔티티 필드가 Team 객체를 참조하니까 Team id와 name 에 바로 접근할 수 있다. */
            Member findMember = em.find(Member.class, member.getId());
            
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());

            /** 팀을 바꾸고 싶다. */
            member.setTeam(teamB);
            System.out.println("팀 소속 바꾸고 나서 팀 이름 = " + member.getTeam().getName());


            tx.commit(); // DB에 insert SQL 실행
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

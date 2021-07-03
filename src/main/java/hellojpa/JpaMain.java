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

            Member member = new Member(); // 멤버 하나 생성
            member.setName("member1");
            member.setTeam(team);  // 팀객체를 바로 세팅한다.
            em.persist(member);

            em.flush(); /** 쓰기지연 SQL 저장소에 있던 쿼리들을 전부 실행한다. (변경 감지된 내용들을 전부 DB에 쿼리 실행하여 반영) */
            em.clear(); /** 영속성 컨텍스트를 초기화 한다. */

            /** Member 엔티티 필드가 Team 객체를 참조하니까 Team id와 name 에 바로 접근할 수 있다. */
            Member findMember = em.find(Member.class, member.getId()); // 멤버 가져오기
            List<Member> members = findMember.getTeam().getMembers();  // 멤버가 속한 팀의 멤버들을 가져오기

            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }

            tx.commit(); // DB에 insert SQL 실행
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

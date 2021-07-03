package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name="team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // 일대다 매핑에서, 상대팀 엔티티의 어느 필드를(칼럼을) 참조하는지 적는다.
    private List<Member> members = new ArrayList<>();

    /** 연관관계 편의 메소드 Team이나 Member 한 쪽에만 구현하기! */
    public void addMember(Member member){
        member.setTeam(member.getTeam());
        members.add(member);
   }

    /**
     [중요 : 연관관계의 주인은 양방향 연관관계에서 다루는 개념이다.]
     연관관계의 주인만이 외래키를 관리할 수 있다.
     주인이 아닌 쪽은 읽기만 가능하다. 그리고 mappedBy 속성으로 주인을 지정해줘야 한다.

     [중요 : 외래키를 사용하는 쪽이 연관관계의 주인이다!]
     Member.team이 연관관계의 주인이다.
     매핑 당하는 Team엔티티의 members에 mappedBy속성을 지정하자.
     mappedBy속성에는 자신을 매핑하고 있는 주인엔티티의 칼럼명을 적어준다. (Member의 team)
     */
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


/**
 [중요!] [ 양방향 연관관계 ] mappedBy 의미

 양방향 연관관계에서 객체와 테이블은 서로 다른방식으로 연관관계를 맺는다.


 [객체]
 Member -> Team 단방향 1개
 Team -> Member 단방향 1개

 객체의 양방향 관계는 이처럼 2개의 단방향 연관관계가 있어야 완성할 수 있다!!


 [테이블]
 FK하나만 있으면 된다. 단방향 연관관계, 양방향 연관관계 모두 FK 하나로 정리된다.


 [객체에서 양방향 연관관계가 있다면, 둘 중 하나로 외래키를 관리해야 한다.]
 Member가 추가될 때, Team 엔티티의 멤버리스트에 멤버가 추가 되어야 하나?
 Team에 멤버가 추가될 때, 멤버가 업데이트 되어야 하나?
 어느 쪽이 기준이 되어야 할까?

 [중요 : 연관관계의 주인은 양방향 연관관계에서 다루는 개념이다.]
 연관관계의 주인만이 외래키를 관리할 수 있다.
 주인이 아닌 쪽은 읽기만 가능하다.
 주인이 아니라면, mappedBy 속성으로 주인을 지정해줘야 한다.

 [중요 : 외래키를 사용하는 쪽이 연관관계의 주인이다!]
 Member엔티티는 Team을 참조한다.
 즉, Member.team이 연관관계의 주인이다.
 매핑 당하는 Team엔티티의 members에 mappedBy속성을 지정하자.
 mappedBy속성에는 자신을 매핑하고 있는 주인엔티티의 칼럼명을 적어준다. (Member의 team)

 @OneToMany(mappedBy = "team") // 일대다 매핑에서, 상대팀 엔티티의 어느 필드를(칼럼을) 참조하는지 적는다.
 private List<Member> members = new ArrayList<>();

 [외래키를 사용하는 쪽이 어디지?]
 일대다 관계에서, '다'쪽이 외래키를 사용한다.
 Member엔티티는 Team엔티티를 FK로서 참조하고 있다.
 이 상황에서는 Member가 외래키를 사용하고 있으므로 연관관계의 주인이 되어야 한다.
 */
}

package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name = "username") // DB 테이블 내의 컬럼명은 name인 것에 매핑된다.
    private String name; // 객체에는 username 으로 쓰고 싶다.

    /** 하나의 팀에 여러 회원이 속할 수 있다.
     * 따라서 멤버가 여럿, 팀이 하나가 된다. 지금 Member 엔티티 입장이니까 @ManyToOne*/
    @ManyToOne // Member 엔티티 입장 중심으로 적는다.
    @JoinColumn(name = "team_id") // Team 엔티티에서 참조하는 실제컬럼명 (FK이름을) 적는다.
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team +
                '}';
    }

    /** [연관관계 편의 메소드]
     '양방향' 연관관계니까, Team에 참조된 Member도 신경써야 한다.
     Member가 속한 Team을 세팅 해 줄 때, 반대편 Team에도 member가 속함을 세팅하자.
     연관관계가 설정된 필드에 대해 엉키지 않도록 setter와는 구분되는 이름을 짓자.

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this); // 멤버에 '나 자신'을 추가한다.
    } */


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

    /**
     * [무한루프 방지 팁]
     * lombok에서 toString 만드는것 쓰지말기. (양방향 연관관계 걸려 있을 때 골치아픔)
     * 컨트롤러에서 절대 엔티티를 바로 반환하지 말기.
     * 왜냐하면, JSON 생성 라이브러리에서, 무한루프 생길 수 있다.
     * 엔티티는 변경될 수 있는 존재다. (연관관계 또는 필드 등..)
     * 엔티티를 json으로 api를 통해 바로 반환하면 API 스펙이 변경되어 버린다. API사용 하는 입장에서 당황.
     *
     * 엔티티는 꼭 DTO(값만 있는)로 변환하여 컨트롤러에서 반환하자.
     */
}

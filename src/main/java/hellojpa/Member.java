package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "USER") // 테이블명과 엔티티명이 다른 경우, 테이블명을 명시해 줄 수 있다
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name = "username") // DB 테이블 내의 컬럼명은 name인 것에 매핑된다.
    private String name; // 객체에는 username 으로 쓰고 싶다.

    @Column(name="team_id")
    private Long teamId;
}

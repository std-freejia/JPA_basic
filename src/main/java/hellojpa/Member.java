package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "USER") // 테이블명과 객체 명이 다른 경우, 테이블명을 명시해 줄 수 있다
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") // DB 테이블 내의 컬럼명은 name인 것에 매핑된다.
    private String username; // 객체에는 username 으로 쓰고 싶다.

    // JPA가 다루는 엔티티는 기본 생성자가 필요하다.
    public Member(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER") // 테이블명과 객체 명이 다른 경우, 테이블명을 명시해 줄 수 있다
public class Member {

    @Id
    private Long id;

    @Column(name="username") // 컬럼명과 필드명이 다른 경우, DB컬럼명을 명시해 줄 수 있다
    private String name;

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
}

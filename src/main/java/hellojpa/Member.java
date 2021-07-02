package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "USER") // 테이블명과 객체 명이 다른 경우, 테이블명을 명시해 줄 수 있다
public class Member {

    @Id
    private Long id;

    @Column(name = "name") // DB 테이블 내의 컬럼명은 name인 것에 매핑된다.
    private String username; // 객체에는 username 으로 쓰고 싶다.
    private Integer age;

    /**
     * DB에는 enum 타입이 없으니까, @Enumerated를 붙여서 처리한다.
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /** DB는 날짜, 시간, 날짜시간을 구분한다.
     * @Temporal(TemporalType.TIMESTAMP)
     * DATE, TIME, TIMESTAMP 에 대한 구분을 해줘야 한다.
     * */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate localDate; // @Temporal 애노테이션 없어도 된다. -> DB의 date 타입에 매핑

    private LocalDateTime localDateTime; //@Temporal 애노테이션 없어도 된다. -> DB의 timestamp 타입에 매핑

    /** 용량이 큰 정보를 담고 싶다면 @Lob를 쓴다.  varchar 보다 큰. */
    @Lob
    private String description;

    /** @Transient DB에 반영하고 싶지 않은 필드 */
    @Transient
    private int temp;

    // JPA가 다루는 엔티티는 기본 생성자가 필요하다.
    public Member(){}
}

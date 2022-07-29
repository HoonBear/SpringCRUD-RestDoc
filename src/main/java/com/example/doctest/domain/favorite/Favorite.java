package com.example.doctest.domain.favorite;

import com.example.doctest.domain.member.Member;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
/**
 * @Setter를 함부로 사용하면
 * Id가 임의의 값으로 세팅되어
 * 불특정 로우가 UPDATE 될 수 있음 (ModelMapper 사용할 경우)
 */
@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; //연관관계의 주인이 된다

    @Setter
    private String favoriteName;

    public void setMember(Member member) {
        this.member = member;
        member.getFavoriteList().add(this);
    }

}
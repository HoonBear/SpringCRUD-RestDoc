package com.example.doctest.domain.member;

import com.example.doctest.domain.favorite.Favorite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    /* cascade -> 부모지워지면 자식 어쩔껀지 정하는 거 (FK로 물려있는 연관데이터 삭제) */
    private List<Favorite> favoriteList = new ArrayList<>();
    @Column(length = 50)
    private String email;
    private String password;
    private String deleteYn;

}


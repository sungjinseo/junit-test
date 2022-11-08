package dev.greatseo.template.api.member.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@DynamicInsert //insert시 null 제외
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 200, nullable = false)
	private String password;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String mobile;

	@Column(length = 100)
	private String nickname;

	@Column(length = 200)
	private String profile;

	@Column(length = 1, nullable = false, columnDefinition = "char(1) default 'N'")
	private String isDeleted;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

	/**
	 * insert 되기전 (persist 되기전) 실행된다.
	 * default 값이 설정되더라도 insert시 null로 셋이되어 오류가 발생한다.
	 * */
	@PrePersist
	public void prePersist() {
		this.isDeleted = this.isDeleted == null ? "N" : this.isDeleted;
	}

	// 게시글 Entity 연관관계 설정(One(회원 Entity) To Many(게시글 Entity)
	//@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private List<Posts> posts = new ArrayList<>();

	// 댓글 Entity 연관관계 설정(One(회원 Entity) To Many(댓글 Entity)
	//@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private List<PostsComment> postsComment = new ArrayList<>();

	@Builder
	public Member(Long id, String email,
                  String password, String name,
                  String mobile, String nickname) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.nickname = nickname;
	}
}

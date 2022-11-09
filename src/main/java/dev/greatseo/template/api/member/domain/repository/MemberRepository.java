package dev.greatseo.template.api.member.domain.repository;

import dev.greatseo.template.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query(value =
            "select count(*) " +
            "  from member " +
            " where email = :email ", nativeQuery = true)
    Integer countByEmail(@Param("email") String email);

    @Query(value =
            "select count(*) " +
            "  from member " +
            " where mobile = :mobile ", nativeQuery = true)
    Integer countByMobile(@Param("mobile") String mobile);
}

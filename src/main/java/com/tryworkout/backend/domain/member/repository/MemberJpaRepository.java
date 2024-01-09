package com.tryworkout.backend.domain.member.repository;

import com.tryworkout.backend.domain.member.data.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}

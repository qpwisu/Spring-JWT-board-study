package com.hany.studybasic.repository;

import com.hany.studybasic.enum_class.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hany.studybasic.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
    Optional<User> findByLoginId(String loginId);
    Long countAllByUserRole(UserRole userRole);
    Page<User> findAllByNicknameContains(String nickname, PageRequest pageRequest);

}

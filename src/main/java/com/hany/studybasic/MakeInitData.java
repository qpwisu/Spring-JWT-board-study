package com.hany.studybasic;

import com.hany.studybasic.enum_class.UserRole;
import com.hany.studybasic.domain.entity.User;
import com.hany.studybasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @PostConstruct
    public void makeAdminAndUser() {
        if (!userRepository.existsByLoginId("admin")) {
            User admin2 = User.builder()
                    .loginId("admin")
                    .password(encoder.encode("1234"))
                    .nickname("관리자")
                    .userRole(UserRole.ADMIN)
                    .build();
            userRepository.save(admin2);
        }

        if (!userRepository.existsByLoginId("user")) {
            User user2 = User.builder()
                    .loginId("user")
                    .password(encoder.encode("1234"))
                    .nickname("유저1")
                    .userRole(UserRole.USER)
                    .build();
            userRepository.save(user2);
        }
    }
}
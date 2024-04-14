package com.hany.studybasic.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.hany.studybasic.enum_class.UserRole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String loginId;
    private String password;
    private String nickname;
    private UserRole userRole;
    private LocalDateTime createdAt;    // 가입 시간

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Board> boards;     // 작성글

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Like> likes;       //
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Comment> comments; // 댓글
    public void edit(String newPassword, String newNickname) {
        this.password = newPassword;
        this.nickname = newNickname;
    }
    public void changeRole() {
        if (userRole.equals(UserRole.USER)) userRole = UserRole.ADMIN;
        else if (userRole.equals(UserRole.ADMIN)) userRole = UserRole.USER;
    }
}

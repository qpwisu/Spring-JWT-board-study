package com.hany.studybasic.domain.dto;

import com.hany.studybasic.domain.entity.Board;
import com.hany.studybasic.domain.entity.Comment;
import com.hany.studybasic.domain.entity.User;
import lombok.Data;

@Data
public class CommentCreateRequest {

    private String body;

    public Comment toEntity(Board board, User user) {
        return Comment.builder()
                .user(user)
                .board(board)
                .body(body)
                .build();
    }
}

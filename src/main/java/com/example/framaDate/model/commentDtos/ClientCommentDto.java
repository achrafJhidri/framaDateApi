package com.example.framadate.model.commentDtos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCommentDto {
    @Getter
    private String comment;
    @Getter
    private Long userId;
}

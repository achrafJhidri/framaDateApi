package com.example.framadate.model.commentDtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCommentDto {
    @Getter
    @NotBlank(message = "leave a valid comment")
    @Size(min = 2)
    private String comment;
    @Getter
    @NotNull
    @PositiveOrZero
    private Long userId;
}

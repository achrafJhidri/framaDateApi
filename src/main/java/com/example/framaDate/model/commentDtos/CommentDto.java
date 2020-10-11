package com.example.framadate.model.commentDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;
    private Date creationDate;
    private Date lastUpdate;
    private Long userId;
}

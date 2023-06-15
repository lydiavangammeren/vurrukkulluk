package com.lydia.vurrukkulluk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private int id;
    private String commentText;
    private UserDto user;
}
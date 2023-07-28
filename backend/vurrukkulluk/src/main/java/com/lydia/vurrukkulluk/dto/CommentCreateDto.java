package com.lydia.vurrukkulluk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {
    private int id;
    @NotBlank
    private String commentText;
    @NotNull
    private int recipeId;
    @NotNull
    private int userId;
}

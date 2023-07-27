package com.lydia.vurrukkulluk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotBlank(message = "must have a name")
    private String name;
    @NotBlank(message = "must have a description")
    private String description;

    @NotNull
    @Min(0)
    private int price;
    @NotNull
    @Min(0)
    private int calories;
    @NotNull
    @Min(1)
    private int amount;
    @NotNull
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id",nullable = true)
    private Image image;
}

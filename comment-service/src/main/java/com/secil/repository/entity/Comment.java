package com.secil.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class Comment extends Base {
    @Id
    private String commentId;
    private String username;
    private String userId;
    private String comment;
    private String recipeId;
    @Builder.Default
    private Long commentDate = System.currentTimeMillis();
}

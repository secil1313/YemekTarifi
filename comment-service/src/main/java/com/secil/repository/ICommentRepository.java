package com.secil.repository;

import com.secil.repository.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByRecipeId(final String recipeId);
}

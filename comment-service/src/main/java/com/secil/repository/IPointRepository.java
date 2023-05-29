package com.secil.repository;

import com.secil.repository.entity.Comment;
import com.secil.repository.entity.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IPointRepository extends MongoRepository<Point,String> {
    List<Point> findByPointId(String pointId);
    Optional<Point> findByUserIdAndRecipeId(String userId, String recipeId);

}

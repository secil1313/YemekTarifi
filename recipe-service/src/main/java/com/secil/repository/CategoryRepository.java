package com.secil.repository;

import com.secil.repository.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);
    boolean existsByCategoryId(String categoryId);
}

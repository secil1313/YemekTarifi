package com.secil.service;

import com.secil.dto.request.CategoryRequestDto;
import com.secil.exception.ErrorType;
import com.secil.exception.RecipeManagerException;
import com.secil.repository.CategoryRepository;
import com.secil.repository.entity.Category;
import com.secil.repository.entity.ERole;
import com.secil.utility.JwtTokenProvider;
import com.secil.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService extends ServiceManager<Category, String> {
    private final CategoryRepository categoryRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CategoryService(CategoryRepository categoryRepository, JwtTokenProvider jwtTokenProvider) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public boolean addCategory(CategoryRequestDto dto) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(dto.getToken());
        if (role.get().equals(ERole.ADMIN.toString())) {
            if (!categoryRepository.existsByCategoryNameIgnoreCase(dto.getCategoryName())) {
                Category category = Category.builder()
                        .categoryName(dto.getCategoryName())
                        .build();
                save(category);
            } else {
                throw new RecipeManagerException(ErrorType.EXIST_CATEGORY);
            }
        } else {
            throw new RecipeManagerException(ErrorType.NOT_ADMIN);
        }
        return true;
    }
    public boolean deleteCategory(CategoryRequestDto dto,String categoryId) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(dto.getToken());
        Optional<Category> category=findById(categoryId);
        if (role.get().equals(ERole.ADMIN.toString())) {
            if (categoryRepository.existsByCategoryNameIgnoreCase(dto.getCategoryName())) {
                deleteById(category.get().getCategoryId());
            }
        } else {
            throw new RecipeManagerException(ErrorType.NOT_ADMIN);
        }
        return true;
    }
    public Boolean existsByCategoryId(String categoryId){
      return categoryRepository.existsByCategoryId(categoryId);
    }



}

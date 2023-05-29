package com.secil.mapper;

import com.secil.dto.request.RecipeRequestDto;
import com.secil.dto.request.SendRecipeAndCategoryId;
import com.secil.dto.request.UpdateRecipeRequestDto;
import com.secil.repository.entity.Recipe;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRecipeMapper {
IRecipeMapper INSTANCE= Mappers.getMapper(IRecipeMapper.class);
Recipe recipeDtoToRecipe(final RecipeRequestDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Recipe updateRecipe(UpdateRecipeRequestDto dto, @MappingTarget Recipe recipe);

SendRecipeAndCategoryId recipeToSendRecipeAndCategoryId(final Recipe recipe);

}

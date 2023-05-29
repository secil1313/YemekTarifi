package com.secil.mapper;

import com.secil.dto.request.*;
import com.secil.dto.response.GetUserForFavoriteCategoryResponseDto;
import com.secil.repository.entity.UserProfile;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE= Mappers.getMapper(IUserProfileMapper.class);
    UserProfile fromDtoToUserProfile(final NewCreateUserRequestDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProfile updateFromDtoToUserProfile(UserProfileUpdateRequestDto dto, @MappingTarget UserProfile userProfile);

    ToAuthUserProfileUpdateRequestDto userUpdateToAuthUpdate(final UserProfile userProfile);
    ToAuthPasswordChangeDto fromUserProfileToAuthPasswordChangeDto(final UserProfile userProfile);
    SendUsernameAndIdRequestDto userProfileToSendUsernameAndIdRequestDto(final UserProfile userProfile);
    UserProfile favoriteRecipeIdToUserProfile(final String favoriteRecipeId);
    @Mapping(target = "recipeId", ignore = true)
    UserProfile sendRecipeAndCategoryIdToUserProfile(final SendRecipeAndCategoryId sendRecipeAndCategoryId);
    GetUserForFavoriteCategoryResponseDto userProfileToGetUserForFavoriteCategoryResponseDto(final UserProfile userProfile);

}

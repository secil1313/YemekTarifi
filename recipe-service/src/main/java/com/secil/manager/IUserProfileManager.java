package com.secil.manager;

import com.secil.dto.response.GetUserForFavoriteCategoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(url = "http://localhost:8090/api/v1/user_profile", name = "recipe-userprofile")
public interface IUserProfileManager {
    @PostMapping("/get-user-with-favorite-category")
    ResponseEntity<Set<GetUserForFavoriteCategoryResponseDto>> getUserForFavoriteCategory (@RequestBody List<String> categoryId);
//list olduğu için pathvariable veremedik, o yuzden postmapping koyduk.
}

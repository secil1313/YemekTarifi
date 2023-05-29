package com.secil.controller;

import com.secil.dto.request.*;
import com.secil.dto.response.GetUserForFavoriteCategoryResponseDto;
import com.secil.repository.entity.UserProfile;
import com.secil.service.UserProfileService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static com.secil.constant.ApiUrls.*;

@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    @Hidden
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody @Valid NewCreateUserRequestDto dto){
      return ResponseEntity.ok(userProfileService.createUser(dto));
    }
    @Hidden
    @PutMapping(ACTIVATE_STATUS+"/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.activateStatus(authId));
    }
    @Hidden
    @PutMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPassword(@RequestBody UserProfileChangePasswordRequestDto dto){
       return ResponseEntity.ok(userProfileService.forgotPassword(dto));
    }
    @PutMapping(UPDATE+"/{token}")
    public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfileUpdateRequestDto dto, @PathVariable String token){
        return ResponseEntity.ok(userProfileService.updateUser(dto,token));
    }
    @DeleteMapping(DELETE + "/{token}")
    public ResponseEntity<Boolean> delete(@PathVariable String token){
        return ResponseEntity.ok(userProfileService.delete(token));
    }
    @PutMapping(PASSWORD_CHANGE)
    public ResponseEntity<Boolean> passwordChange(@RequestBody PasswordChangeDto dto){
        return ResponseEntity.ok(userProfileService.passwordChange(dto));
    }
    @Hidden
    @GetMapping(SEND_USERNAME_USERID+"/{authId}")
    public ResponseEntity<SendUsernameAndIdRequestDto> send(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.sendfindOptionalByAuthId(authId));
    }
    @PostMapping(TAKING_FAVORITE+"/{recipeId}/{token}")
    public ResponseEntity<Boolean> takeRecipeToFavorites(@PathVariable String recipeId,@PathVariable String token){
        return ResponseEntity.ok(userProfileService.takeRecipeToFavorites(recipeId,token));
    }
    @Hidden
    @PostMapping(GET_USER_WITH_FAVORITE_CATEGORY)
    ResponseEntity<Set<GetUserForFavoriteCategoryResponseDto>> getUserForFavoriteCategory (@RequestBody List<String> categoryId){
        //list olduğu için pathvariable veremedik, o yuzden postmapping koyduk.
        return ResponseEntity.ok(userProfileService.getUserWithFavoriteCategoryFromRecipeService(categoryId));
    }
}

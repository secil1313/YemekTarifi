package com.secil.controller;

import com.secil.dto.request.AddCommentRequestDto;
import com.secil.service.CommentService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.secil.constant.ApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
public class CommentController {
    private final CommentService commentService;
    @PostMapping(ADD_COMMENT+"/{token}")
    @Operation(summary = "AddComment")
    public ResponseEntity<Boolean> add( @PathVariable String token,@RequestBody @Valid AddCommentRequestDto dto){
      return  ResponseEntity.ok(commentService.addComment(token,dto));
    }

    @DeleteMapping(DELETE+"/{commentId}/{token}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable String commentId, @PathVariable String token){
        return ResponseEntity.ok(commentService.deleteComment(commentId,token));
    }
    @PostMapping(COMMENT_TO_RECIPEMANAGER+"/{recipeId}")
    @Hidden
    public ResponseEntity<Boolean> commentToRecipeManager(@PathVariable String recipeId){
        return ResponseEntity.ok(commentService.deleteCommentBecauseOfRecipe(recipeId));
    }

}

package com.secil.service;

import com.secil.dto.request.AddCommentRequestDto;
import com.secil.dto.request.SendUsernameAndIdRequestDto;
import com.secil.exception.CommentManagerException;
import com.secil.exception.ErrorType;
import com.secil.manager.IRecipeManager;
import com.secil.manager.IUserProfileManager;
import com.secil.repository.ICommentRepository;
import com.secil.repository.entity.Comment;
import com.secil.repository.entity.ERole;
import com.secil.utility.JwtTokenProvider;
import com.secil.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment, String> {
    private final ICommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserProfileManager userProfileToCommentManager;
    private final IRecipeManager commentToRecipeManager;

    public CommentService(ICommentRepository commentRepository, JwtTokenProvider jwtTokenProvider, IUserProfileManager userProfileToCommentManager, IRecipeManager commentToRecipeManager) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userProfileToCommentManager = userProfileToCommentManager;
        this.commentToRecipeManager = commentToRecipeManager;
    }

    public Boolean addComment(String token, AddCommentRequestDto dto) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new CommentManagerException(ErrorType.USER_NOT_FOUND);
        }
        Boolean status = commentToRecipeManager.existByRecipeId(dto.getRecipeId()).getBody();
        if (status == true) {
            if (role.get().equals(ERole.USER.toString())) {
                SendUsernameAndIdRequestDto takeUsernameAndIdRequestDto = userProfileToCommentManager.send(authId.get()).getBody();
                Comment comment = Comment.builder()
                        .comment(dto.getComment())
                        .recipeId(dto.getRecipeId())
                        .build();
                comment.setUserId(takeUsernameAndIdRequestDto.getUserId());
                comment.setUsername(takeUsernameAndIdRequestDto.getUsername());
                save(comment);
                commentToRecipeManager.recipeToCommentManager(comment.getCommentId(), comment.getRecipeId());
                return true;
            } else {
                throw new CommentManagerException(ErrorType.NOT_ADMIN);
            }
        } else {
            throw new CommentManagerException(ErrorType.RECIPE_NOT_FOUND);
        }
    }

    public Boolean deleteComment(String commentId, String token) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Optional<Comment> comment = findById(commentId);
        if(comment.isEmpty())throw new CommentManagerException(ErrorType.COMMENT_NOT_FOUND);
        if (role.get().equals(ERole.USER.toString())) {
        delete(comment.get());}
        commentToRecipeManager.deleteCommentFromRecipe(comment.get().getRecipeId(), commentId);
        return true;
    }

    public Boolean deleteCommentBecauseOfRecipe(String recipeId) {
        List<Comment> comments = commentRepository.findByRecipeId(recipeId);
        for (Comment comment : comments) {
            deleteById(comment.getCommentId());
        }
        return true;
    }


}

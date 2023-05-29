package com.secil.service;

import com.secil.dto.request.AddPointRequestDto;
import com.secil.dto.request.SendUsernameAndIdRequestDto;
import com.secil.exception.CommentManagerException;
import com.secil.exception.ErrorType;
import com.secil.manager.IRecipeManager;
import com.secil.manager.IUserProfileManager;
import com.secil.mapper.IPointMapper;
import com.secil.repository.IPointRepository;
import com.secil.repository.entity.ERole;
import com.secil.repository.entity.Point;
import com.secil.utility.JwtTokenProvider;
import com.secil.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointService extends ServiceManager<Point, String> {
    private final IPointRepository pointRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IRecipeManager commentToRecipe;
    private final IRecipeManager commentToRecipeManager;
    private final IUserProfileManager commentManager;

    public PointService(IPointRepository pointRepository, JwtTokenProvider jwtTokenProvider, IRecipeManager commentToRecipe, IRecipeManager commentToRecipeManager, IUserProfileManager commentManager) {
        super(pointRepository);
        this.pointRepository = pointRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.commentToRecipe = commentToRecipe;
        this.commentToRecipeManager = commentToRecipeManager;
        this.commentManager = commentManager;
    }

    public Boolean addPoint(String token, AddPointRequestDto dto) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);

        if (authId.isEmpty()) {
            throw new CommentManagerException(ErrorType.USER_NOT_FOUND);
        }
        Boolean status = commentToRecipeManager.existByRecipeId(dto.getRecipeId()).getBody();
        if (status == true) {
            if (role.get().equals(ERole.USER.toString())) {
                SendUsernameAndIdRequestDto sendUsernameAndIdRequestDto = commentManager.send(authId.get()).getBody();
                Optional<Point> point = pointRepository.findByUserIdAndRecipeId(sendUsernameAndIdRequestDto.getUserId(), dto.getRecipeId());
                if (point.isEmpty()) {
                    Point newPoint = IPointMapper.INSTANCE.addPointDtoToPoint(dto);
                    newPoint.setUserId(sendUsernameAndIdRequestDto.getUserId());
                    save(newPoint);
                    commentToRecipeManager.recipeToPointManager(newPoint.getPointId(), newPoint.getRecipeId());
                } else {
                    point.get().setPoint(dto.getPoint());
                    update(point.get());
                }
                return true;
            } else {
                throw new CommentManagerException(ErrorType.NOT_ADMIN);
            }
        } else {
            throw new CommentManagerException(ErrorType.RECIPE_NOT_FOUND);
        }
    }

    public Boolean deletePoint(String pointId, String token) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Optional<Point> point = findById(pointId);
        if (point.isEmpty()) throw new CommentManagerException(ErrorType.POINT_NOT_FOUND);
        if (role.get().equals(ERole.USER.toString())) {
            delete(point.get());
        }
        commentToRecipeManager.deletePointFromRecipe(point.get().getRecipeId(), pointId);
        return true;
    }

    public Boolean deletePointBecauseOfRecipe(String pointId) {
        List<Point> points = pointRepository.findByPointId(pointId);
        for (Point point : points) {
            deleteById(point.getPointId());
        }
        return true;
    }

}




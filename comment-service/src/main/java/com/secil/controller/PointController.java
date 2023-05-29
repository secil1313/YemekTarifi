package com.secil.controller;
import com.secil.dto.request.AddPointRequestDto;
import com.secil.service.PointService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.secil.constant.ApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(POINT)
public class PointController {
    private final PointService pointService;
    @PostMapping(ADD+"/{token}")
    @Operation(summary = "AddPoint")
    public ResponseEntity<Boolean> addPoint(@PathVariable String token, @RequestBody @Valid AddPointRequestDto dto) {
        return ResponseEntity.ok(pointService.addPoint(token,dto));
    }
    @DeleteMapping(DELETE+"/{pointId}/{token}")
    public ResponseEntity<Boolean> deletePoint(@PathVariable String pointId, @PathVariable String token) {
        return ResponseEntity.ok(pointService.deletePoint(pointId,token));
    }
    //TODO:Aşağıdaki comment controllerla aynıpostmappigin içindeki adı!!!!
    @PostMapping(COMMENT_TO_RECIPEMANAGER+"/{pointId}")
    @Hidden
    public ResponseEntity<Boolean> commentToRecipeManagerPointId(@PathVariable String pointId){
        return ResponseEntity.ok(pointService.deletePointBecauseOfRecipe(pointId));
    }

}

package com.secil.controller;
import com.secil.dto.request.CategoryRequestDto;
import com.secil.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.secil.constant.ApiUrls.*;

@RestController
@RequestMapping(CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping(ADD)
    public ResponseEntity<Boolean> addCategory(@RequestBody CategoryRequestDto dto){
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }

}


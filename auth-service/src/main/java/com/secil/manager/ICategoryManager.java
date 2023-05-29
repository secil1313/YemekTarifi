package com.secil.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(url = "http://localhost:8080/api/v1/category",name="auth-category")

public interface ICategoryManager {


}

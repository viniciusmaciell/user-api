package com.letscode.ecommerce.userapi.controller;

import com.letscode.ecommerce.userapi.domain.UserEntity;
import com.letscode.ecommerce.userapi.domain.UserRequest;
import com.letscode.ecommerce.userapi.domain.UserResponse;
import com.letscode.ecommerce.userapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest userRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable(value = "id") Long userId) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable(value = "id") Long id,
                                               @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId,
                                             @RequestBody UserRequest userRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(userId, userRequest));
    }

}

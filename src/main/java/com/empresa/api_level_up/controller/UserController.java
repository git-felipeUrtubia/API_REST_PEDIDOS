package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.request.UserRequestDTO;
import com.empresa.api_level_up.dto.response.UserResponseDTO;
import com.empresa.api_level_up.model.Token;
import com.empresa.api_level_up.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO body =  userService.saveUser(userRequestDTO);
            return ResponseEntity.ok(body);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        try {
            List<UserResponseDTO> body = userService.findAllUsers();
            if (body.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(body);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

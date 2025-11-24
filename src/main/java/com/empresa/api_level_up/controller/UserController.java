package com.empresa.api_level_up.controller;

import com.empresa.api_level_up.dto.request.UserRequestDTO;
import com.empresa.api_level_up.dto.response.UserResponseDTO;
import com.empresa.api_level_up.model.Token;
import com.empresa.api_level_up.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con el usuarios")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @Operation(summary = "Guardar Usuario", description = "Obtiene el usuario que a sido guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "500", description = "Usuario no guardado")
    })
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO body =  userService.saveUser(userRequestDTO);
            return ResponseEntity.ok(body);

    //      {
    //        "firstNameUser": "string",
    //        "lastNameUser": "string",
    //        "emailUser": "string",
    //        "passwordUser": "string",
    //        "rolUser": "string",
    //        "cliente": {
    //        "firstName": "string",
    //        "lastName": "string"
    //        },
    //        "tokens": []
    //      }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
    })
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

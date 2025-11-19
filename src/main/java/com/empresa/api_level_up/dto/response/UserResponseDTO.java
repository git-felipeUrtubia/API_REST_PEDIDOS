package com.empresa.api_level_up.dto.response;

import com.empresa.api_level_up.model.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String emailUser;
    private String password;
    private String rolUser;
    private ClienteDTO cliente;
    private List<TokenDTO> tokens;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClienteDTO {
        private String firstName;
        private String lastName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TokenDTO {
        private Long id_token;
    }

    public static class LoginDTO {

    }
}

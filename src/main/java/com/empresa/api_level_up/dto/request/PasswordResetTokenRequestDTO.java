package com.empresa.api_level_up.dto.request;

import lombok.Data;

@Data
public class PasswordResetTokenRequestDTO {

    @Data
    public static class Solicitar {
        private String email;
    }

    @Data
    public static class Reestablecer {
        private String token;
        private String newPassword;
    }
}

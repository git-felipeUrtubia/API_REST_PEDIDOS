package com.empresa.api_level_up.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {

    private String token;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate expired_token;
    private String estado_token;
    private Long id_user;

}

package com.empresa.api_level_up.dto.request;


import com.empresa.api_level_up.model.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String emailUser;
    private String passwordUser;
    private String rolUser;
    private ClienteRequestDTO cliente;
    private List<Token> tokens;

}

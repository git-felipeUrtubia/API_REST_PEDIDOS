package com.empresa.api_level_up.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_token;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDate expired_token;

    @Column(nullable = false)
    private String estado_token;

    @JsonBackReference("user-token")
    @ManyToOne
    private User user;

}

package com.empresa.api_level_up.service;


import com.empresa.api_level_up.dto.request.PasswordResetTokenRequestDTO;
import com.empresa.api_level_up.model.PasswordResetToken;
import com.empresa.api_level_up.model.User;
import com.empresa.api_level_up.repository.PasswordResetTokenRepository;
import com.empresa.api_level_up.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository resetRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String SolicitarPassword(PasswordResetTokenRequestDTO.Solicitar req) {

        try {
            String token = UUID.randomUUID().toString();

            PasswordResetToken passdReset = new PasswordResetToken();
            passdReset.setToken(token);
            passdReset.setEmail(req.getEmail());
            passdReset.setExpiration(LocalDateTime.now().plusMinutes(5));

            resetRepo.save(passdReset);

            // Crear link frontend
            String link = "http://localhost:5173/reset-password?token=" + token;

            // Enviar email
            sendEmail(req.getEmail(), link);

            return "Te enviamos un enlace de recuperación a tu correo.";

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String ResetPassword(PasswordResetTokenRequestDTO.Reestablecer req) {

        try {
            PasswordResetToken token = resetRepo.findByToken(req.getToken())
                    .orElseThrow(() -> new RuntimeException("Token inválido"));

            if (token.getExpiration().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Token expirado");
            }

            List<User> users = userRepo.findAll();
            User user = new User();
            for (User u : users) {
                if (u.getEmail_user().equals(token.getEmail())) {
                    user = u;
                }
            }

            user.setPassword_user(req.getNewPassword());
            userRepo.save(user);

            resetRepo.delete(token);

            return "Contraseña actualizada correctamente.";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void sendEmail(String email, String link) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Restablecer contraseña");
        msg.setText("Haz clic en el siguiente enlace para restablecer tu contraseña:\n\n" + link);

        mailSender.send(msg);
    }


}

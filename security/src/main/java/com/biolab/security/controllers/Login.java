package com.biolab.security.controllers;

import com.biolab.security.dto.LoginDTO;
import com.biolab.security.entities.Usuario;
import com.biolab.security.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Login {

    private final CustomUserDetailsService userDetailsService;

    public Login(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        try {
            Usuario u = (Usuario) userDetailsService.loadUserByUsername(dto.getCpf());


            if (!dto.getSenha().equals(u.getSenha())) {
                return ResponseEntity.ok("usuario ou senha incorretos");
            }

            return ResponseEntity.ok("Logado com sucesso");

        } catch (Exception e) {
            return ResponseEntity.ok("usuario ou senha incorretos");
        }
    }
}
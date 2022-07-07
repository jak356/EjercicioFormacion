package com.example.BackEmpresa.security;

import com.example.BackEmpresa.user.domain.UserEntity;
import com.example.BackEmpresa.user.infrastructure.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v0-empresa/token")
public class TokenController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestHeader("email") String email, @RequestHeader("password") String pwd) {
        UserEntity user = userRepository.findByEmail(email);
        if (!user.getPassword().equals(pwd)) {
           return new ResponseEntity<>("",HttpStatus.FORBIDDEN);
        }
        if (!user.getEmail().equals("admin@adminbus.local")) {
            return new ResponseEntity<>(getJWTToken(user.getIdUser(), email, "ADMIN"), HttpStatus.OK);
        }
        return new ResponseEntity<>(getJWTToken(user.getIdUser(), email, "USER"), HttpStatus.OK);
    }

    @GetMapping("{token}")
    public ResponseEntity<Void> checkToken(@PathVariable String token) {
        if (this.verifyToken(token))

            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private String getJWTToken(UUID id, String email, String rol) {
        String secretKey = "${secretKey}";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId(String.valueOf(id))
                .setSubject(email)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    private boolean verifyToken(String token) {
        final String SECRET = "${secretKey}";
        final String PREFIX = "Bearer ";
        try {
            String jwtToken = token.replace(PREFIX, "");
            Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

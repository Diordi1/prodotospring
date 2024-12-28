package com.apptester.app12;

import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.sql.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class authcontroller {
    @Autowired
    userrep ur;

    @PostMapping("/signup")
    public ResponseEntity<?> getMethodName(@RequestBody user us) {
        SecretKey sk = Keys.hmacShaKeyFor(jwtvalida.key.getBytes());
        String jwt = Jwts.builder().signWith(sk).claim("email", us.getEmail()).setIssuedAt(new java.util.Date())
                .claim("authorites", "ROLE_ADMIN").compact();
        ur.save(us);

        return new ResponseEntity<>(jwt, HttpStatus.ACCEPTED);

    }

    @PostMapping("/signin")
    public ResponseEntity<?> setSinin(@RequestBody user us) {
        SecretKey ks = Keys.hmacShaKeyFor(jwtvalida.key.getBytes());
        if (ur.findByEmail(us.getEmail()).size() > 0) {
            if (ur.findByEmail(us.getEmail()).get(0).getPassword().equals(us.getPassword())) {
                String jwt = Jwts.builder().signWith(ks).claim("email", us.getEmail())
                        .claim("Authorities", "ROLE_ADMIN")
                        .setIssuedAt(new java.util.Date())
                        .setExpiration(new java.util.Date(new java.util.Date().getTime() + 1000 * 60 * 60))
                        .compact();

                System.out.println(new java.util.Date());
                Authentication auth = new UsernamePasswordAuthenticationToken(us.getEmail(), null);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return new ResponseEntity<>(jwt, HttpStatus.OK);

            } else {
                return new ResponseEntity<>("Incorrect Creditials", HttpStatus.BAD_REQUEST);

            }
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);

        }
    }

}

package com.apptester.app12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtfilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String key = request.getHeader(jwtvalida.header);
        if (key != null) {
            String jwt = key.substring(7);
            System.out.println(jwt);

            SecretKey sk = Keys.hmacShaKeyFor(jwtvalida.key.getBytes());
            Claims clk = Jwts.parser().setSigningKey(sk).build().parseClaimsJws(jwt).getBody();
            String email = String.valueOf(clk.get("email"));
            // String username = String.valueOf(clk.get("username"));
            // String authorites = String.valueOf(clk.get("authorites"));
            List<GrantedAuthority> l1 = new ArrayList<>();

            Authentication auth = new UsernamePasswordAuthenticationToken(email, null, l1);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(request, response);

        // TODO Auto-generated method stub

        // throw new UnsupportedOperationException("Unimplemented method
        // 'doFilterInternal'");
    }

}

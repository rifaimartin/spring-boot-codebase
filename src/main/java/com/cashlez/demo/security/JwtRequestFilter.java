package com.cashlez.demo.security;

import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.repo.MerchantRepository;
import com.cashlez.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public static final String DELIMITER = " ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String authorizationHeader = request.getHeader(JwtProperty.HEADER_AUTHORIZATION);

        String username = null;
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(JwtProperty.TYPE.concat(DELIMITER))) {
            token = authorizationHeader.split(DELIMITER)[1];
            username = jwtUtil.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Merchant> merchantOptional = merchantRepository.findByUserName(username);
            if (jwtUtil.isTokenValid(token, merchantOptional.get())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(merchantOptional, null, null);
                authenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}

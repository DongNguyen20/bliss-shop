package com.blissshop.authserver.service.impl;

import com.blissshop.authserver.model.LoginRequest;
import com.blissshop.authserver.model.LoginResponse;
import com.blissshop.authserver.security.JwtTokenUtil;
import com.blissshop.authserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Transactional
    public LoginResponse login(LoginRequest request) throws Exception{
        return doLogin(request.getUsername(), request.getPassword());
    }

    private LoginResponse doLogin(String userName, String password) throws Exception{
        try {
            // Get user from database
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            // compare password
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid username or password");
            }

            var token = jwtTokenUtil.generateToken(userDetails);

            return LoginResponse.builder()
                    .accessToken(token)
                    .refreshToken(token)
                    .build();
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
    }
}

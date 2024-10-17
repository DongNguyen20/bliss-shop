package com.blissshop.authserver.service;

import com.blissshop.authserver.model.LoginRequest;
import com.blissshop.authserver.model.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest) throws Exception;
}

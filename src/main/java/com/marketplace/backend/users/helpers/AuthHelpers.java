package com.marketplace.backend.users.helpers;

import com.marketplace.backend.users.dtos.JwtRespDTO;
import com.marketplace.backend.users.dtos.LoginDTO;

public interface AuthHelpers {
    JwtRespDTO login(LoginDTO  loginDTO);
    JwtRespDTO logout(String token);
}

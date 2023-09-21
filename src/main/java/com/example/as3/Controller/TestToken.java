package com.example.as3.Controller;

import com.example.as3.Config.JwtConfig;
import com.example.as3.Security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestToken {
    @Autowired
    protected JwtConfig jwtConfig;

    @GetMapping("/token")
    public String getToken() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        String token = jwtConfig.generateToken(userSecurity);
        return token != null ? "Token is: " + token : "Token is invalid/null.";
    }
}

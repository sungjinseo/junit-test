package dev.greatseo.template.util.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AuthProviderTest {

    @Autowired
    private AuthProvider authProvider;

    @Test
    public void 토큰발행_test(){
        String token = authProvider.createToken(1L, "sungjin.seo", "USER");
        System.out.println(token);
    }
}
package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.common.core.domain.Result;
import com.gls.gemini.common.core.enums.ResultEnums;
import com.gls.gemini.uaa.boot.constants.UaaSecurityProperties;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private JwtEncoder jwtEncoder;
    @Resource
    private UaaSecurityProperties securityProperties;

    @PostMapping("/token")
    public Result<String> token(Authentication authentication) {
        Instant now = Instant.now();
        long expiration = securityProperties.getJwt().getExpiration();
        String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(securityProperties.getJwt().getIssuer())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return ResultEnums.SUCCESS.getResult(this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}

package com.gls.gemini.uaa.boot.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

/**
 * jwt token提供者
 */
@Slf4j
public class JwtTokenProvider {

    private final String secretKey;

    private final long validityInMilliseconds;

    public JwtTokenProvider(String secretKey, long validityInMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(String username) throws JOSEException {
        // 创建JWS头，设置JWS算法和类型
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT).build();
        // 将负载设置为JWTClaimsSet实例
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new java.util.Date())
                .expirationTime(new java.util.Date(System.currentTimeMillis() + validityInMilliseconds))
                .build();
        // 创建JWS对象
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        // 创建HMAC签名器
        MACSigner macSigner = new MACSigner(secretKey);
        // 签名
        signedJWT.sign(macSigner);
        // 生成token
        return signedJWT.serialize();
    }
}

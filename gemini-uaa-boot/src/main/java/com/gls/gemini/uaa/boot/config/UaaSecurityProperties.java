package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.common.core.base.BaseProperties;
import com.gls.gemini.common.core.constant.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = CommonConstants.BASE_PROPERTIES_PREFIX + ".uaa.security")
public class UaaSecurityProperties extends BaseProperties {

    private JwtProperties jwt = new JwtProperties();

    @Data
    public static class JwtProperties implements Serializable {

        private RSAPublicKey publicKey;

        private RSAPrivateKey privateKey;

        private long expiration = 3600;

        private String issuer = "https://uaa.gemini.gls.com";
    }
}

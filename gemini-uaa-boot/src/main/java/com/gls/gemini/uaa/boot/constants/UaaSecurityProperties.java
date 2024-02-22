package com.gls.gemini.uaa.boot.constants;

import com.gls.gemini.common.core.base.BaseProperties;
import com.gls.gemini.common.core.constant.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = CommonConstants.BASE_PROPERTIES_PREFIX + ".uaa.security")
public class UaaSecurityProperties extends BaseProperties {

    private JwtProperties jwt = new JwtProperties();

    private AuthorizationServerProperties authorizationServer = new AuthorizationServerProperties();

    @Data
    public static class JwtProperties implements Serializable {

        private long expiration = 3600;

        private String issuer = "https://uaa.gemini.gls.com";
    }

    @Data
    public static class AuthorizationServerProperties implements Serializable {

        private String issuer = "https://uaa.gemini.gls.com";
    }
}

package com.gls.gemini.uaa.sdk.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequestVo implements Serializable {

    private String username;

    private String password;
}

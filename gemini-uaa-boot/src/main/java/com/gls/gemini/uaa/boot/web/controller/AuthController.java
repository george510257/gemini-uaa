package com.gls.gemini.uaa.boot.web.controller;

import jakarta.annotation.Resource;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private RegisteredClientRepository clientRepository;

    @Resource
    private OAuth2AuthorizationConsentService authorizationConsentService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/consent")
    public String consent(Principal principal, Model model,
                          @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
                          @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
                          @RequestParam(OAuth2ParameterNames.STATE) String state,
                          @RequestParam(value = OAuth2ParameterNames.USER_CODE, required = false) String userCode) {
        // 根据客户端ID查询注册客户端
        RegisteredClient client = clientRepository.findByClientId(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Invalid client id: " + clientId);
        }
        // 根据客户端ID和主体查询授权同意
        OAuth2AuthorizationConsent consent = authorizationConsentService.findById(clientId, principal.getName());

        Set<String> scopesToApprove = new HashSet<>();
        Set<String> previouslyApprovedScopes = new HashSet<>();
        Set<String> approvedScopes = consent != null ? consent.getScopes() : new HashSet<>();
        Set<String> requestedScopes = new HashSet<>(Set.of(scope.split(" ")));

        for (String requestedScope : requestedScopes) {
            if (approvedScopes.contains(requestedScope)) {
                previouslyApprovedScopes.add(requestedScope);
            } else {
                scopesToApprove.add(requestedScope);
            }
        }

        model.addAttribute("clientId", clientId);
        model.addAttribute("state", state);
        model.addAttribute("scopes", scopesToApprove);
        model.addAttribute("previouslyApprovedScopes", previouslyApprovedScopes);
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("userCode", userCode);
        if (userCode != null) {
            model.addAttribute("requestURI", "/oauth2/device_verification");
        } else {
            model.addAttribute("requestURI", "/oauth2/authorize");
        }

        return "consent";
    }
}

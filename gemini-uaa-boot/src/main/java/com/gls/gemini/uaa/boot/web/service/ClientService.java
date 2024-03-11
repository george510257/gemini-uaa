package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.uaa.boot.web.converter.ClientConverter;
import com.gls.gemini.upms.sdk.feign.ClientInfoFeign;
import com.gls.gemini.upms.sdk.vo.ClientInfoVo;
import jakarta.annotation.Resource;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements RegisteredClientRepository {
    @Resource
    private ClientInfoFeign clientInfoFeign;
    @Resource
    private ClientConverter clientConverter;

    @Override
    public void save(RegisteredClient registeredClient) {
        ClientInfoVo vo = clientInfoFeign.getByClientId(registeredClient.getClientId()).getData();
        if (vo == null) {
            vo = clientConverter.convert(registeredClient);
            clientInfoFeign.insert(vo);
        } else {
            clientConverter.convertCopy(registeredClient, vo);
            clientInfoFeign.update(vo.getId(), vo);
        }
    }

    @Override
    public RegisteredClient findById(String id) {
        ClientInfoVo vo = clientInfoFeign.get(Long.valueOf(id)).getData();
        if (vo != null) {
            return clientConverter.reverse(vo);
        }
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientInfoVo vo = clientInfoFeign.getByClientId(clientId).getData();
        if (vo != null) {
            return clientConverter.reverse(vo);
        }
        return null;
    }
}

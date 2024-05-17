package com.gls.gemini.uaa.boot.web.service;

import cn.hutool.core.util.NumberUtil;
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
    private ClientConverter clientConverter;
    @Resource
    private ClientInfoFeign clientInfoFeign;

    @Override
    public void save(RegisteredClient registeredClient) {
        ClientInfoVo clientInfoVo = clientConverter.convert(registeredClient);
        if (clientInfoVo.getId() == null) {
            clientInfoFeign.insert(clientInfoVo);
        } else {
            clientInfoFeign.update(clientInfoVo.getId(), clientInfoVo);
        }
    }

    @Override
    public RegisteredClient findById(String id) {
        return clientConverter.reverse(clientInfoFeign.get(NumberUtil.parseLong(id)).getData());
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientConverter.reverse(clientInfoFeign.getByClientId(clientId).getData());
    }
}

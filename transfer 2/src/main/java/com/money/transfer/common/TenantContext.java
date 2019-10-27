package com.money.transfer.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TenantContext {

    public Map<String, RequestConfig> nodeRequestConfigs;

    public RequestConfig currentTenant;

    public TenantContext(@Value("${nodes}") String nodes) throws IOException {
        nodeRequestConfigs = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, RequestConfig> parsedNodeConfigs = objectMapper.readValue(nodes, new TypeReference<Map<String, RequestConfig>>() {
        });

        for (Map.Entry<String, RequestConfig> keyValuePair : parsedNodeConfigs.entrySet()) {
            String name = keyValuePair.getKey();
            RequestConfig requestConfig = keyValuePair.getValue();
            requestConfig.setName(name);
            nodeRequestConfigs.put(name, requestConfig);
        }
        System.out.println(nodeRequestConfigs.toString());
    }

    public RequestConfig getCurrentTenant() {
        return currentTenant;
    }

    public void setCurrentTenant(String nodeName) {
        this.currentTenant = nodeRequestConfigs.get(nodeName);
    }

    public String getConstellationKeyForNode(String toNode) {
        return nodeRequestConfigs.get(toNode).getConstellationPub();
    }

    public Map<String, RequestConfig> getNodeRequestConfigs() {
        return nodeRequestConfigs;
    }
}

package com.money.transfer.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestConfig {

    String name;

    @JsonProperty("constellationPub")
    String constellationPub;

    @JsonProperty("quorumIp")
    String quorumIp;

    @JsonProperty("quorumPort")
    String quorumPort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstellationPub() {
        return constellationPub;
    }

    public void setConstellationPub(String constellationPub) {
        this.constellationPub = constellationPub;
    }

    public String getQuorumIp() {
        return quorumIp;
    }

    public void setQuorumIp(String quorumIp) {
        this.quorumIp = quorumIp;
    }

    public String getQuorumPort() {
        return quorumPort;
    }

    public void setQuorumPort(String quorumPort) {
        this.quorumPort = quorumPort;
    }

    @Override
    public String toString() {
        return "RequestConfig{" +
            "name='" + name + '\'' +
            ", constellationPub='" + constellationPub + '\'' +
            ", quorumIp='" + quorumIp + '\'' +
            ", quorumPort='" + quorumPort + '\'' +
            '}';
    }
}

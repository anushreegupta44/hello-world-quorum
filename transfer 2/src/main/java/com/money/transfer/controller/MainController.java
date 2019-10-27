package com.money.transfer.controller;

import com.money.transfer.common.RequestConfig;
import com.money.transfer.common.TenantContext;
import com.money.transfer.service.SayHiService;
import contracts.com.hello.world.ContractRegistry;
import contracts.com.hello.world.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.Log;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    SayHiService sayHiService;

    @Autowired
    TenantContext tenantContext;

    @GetMapping("/from/{fromNode}/toNode/{toNode}/sayHi/{contractRegistryAddress}")
    public HelloWorld sayHiToTheBlockchain(@PathVariable("toNode") String toNode, @PathVariable("contractRegistryAddress") String contractRegistryAddress) throws Exception {
        return sayHiService.deployGreetingContract(toNode, contractRegistryAddress);
    }

    @GetMapping("/from/{fromNode}/updateEvents")
    public List<Log> getAllNode1Events() {
        RequestConfig requestConfig = tenantContext.getCurrentTenant();
        sayHiService.getAllNodeEvents(requestConfig);
        return null;
    }

    @GetMapping("/from/{fromNode}/contractRegistry")
    public ContractRegistry deployContractRegistry() throws Exception {
        return sayHiService.deployContractRegistry();
    }


}

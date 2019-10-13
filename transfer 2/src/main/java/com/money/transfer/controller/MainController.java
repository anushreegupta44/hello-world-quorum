package com.money.transfer.controller;

import com.money.transfer.service.SayHiService;
import contracts.com.hello.world.ContractRegistry;
import contracts.com.hello.world.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    SayHiService sayHiService;

    @GetMapping("/sayHi/{contractRegistryAddress}")
    public HelloWorld sayHiToTheBlockchain(@PathVariable("contractRegistryAddress") String contractRegistryAddress) throws Exception {
        return sayHiService.deployGreetingContract(contractRegistryAddress);
    }

    @GetMapping("/sayHiBack/{message}/{contractRegistryAddress}")
    public TransactionReceipt sayHiBack(@PathVariable("message") String message, @PathVariable("contractRegistryAddress") String contractRegistryAddress) throws Exception {
        return sayHiService.sayHiBack(message, contractRegistryAddress);
    }

    @GetMapping("/updateEvents")
    public List<Log> getAllNode1Events() {
        sayHiService.getAllNodeEvents();
        return null;
    }

    @GetMapping("/contractRegistry")
    public ContractRegistry deployContractRegistry() throws Exception {
        return sayHiService.deployContractRegistry();
    }


}

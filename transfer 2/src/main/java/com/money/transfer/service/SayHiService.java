package com.money.transfer.service;

import com.money.transfer.common.RequestConfig;
import com.money.transfer.common.TenantContext;
import contracts.com.hello.world.ContractRegistry;
import contracts.com.hello.world.HelloWorld;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;
import org.web3j.utils.Async;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.web3j.tx.Contract.staticExtractEventParameters;

@Service
public class SayHiService {


    public TenantContext tenantContext;

    public SayHiService(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
    }

    public HelloWorld deployGreetingContract(String toNode, String contractRegistryAddress) throws Exception {
        ScheduledExecutorService scheduledExecutorService = Async.defaultExecutorService();
        Admin admin = Admin.build(getHttpService(tenantContext.getCurrentTenant().getQuorumIp() + ":" + tenantContext.getCurrentTenant().getQuorumPort()), 100, scheduledExecutorService);
        Quorum quorum = Quorum.build(getHttpService(tenantContext.getCurrentTenant().getQuorumIp() + ":" + tenantContext.getCurrentTenant().getQuorumPort()));
        String address = admin.ethAccounts().send().getAccounts().get(0);
        String fromNodeKey = tenantContext.getCurrentTenant().getConstellationPub();
        String toNodeKey = tenantContext.getConstellationKeyForNode(toNode);
        List list = new ArrayList();
        list.add(fromNodeKey);
        list.add(toNodeKey);
        ClientTransactionManager clientTransactionManager = getPrivateTransactionManager(quorum, address, list, 40, 100);
        ContractRegistry contractRegistry = ContractRegistry
            .load(contractRegistryAddress, admin, clientTransactionManager, BigInteger.valueOf(0), BigInteger.valueOf(100000000));
        String contractAddress = contractRegistry.getContract("node1", "node7").send();
        System.out.println("Hello world contrcat address " + contractAddress);
        HelloWorld helloWorld;
        if (!contractAddress.equals("0x0000000000000000000000000000000000000000")) {
            helloWorld = HelloWorld.load(contractAddress, admin, clientTransactionManager, BigInteger.valueOf(0), BigInteger.valueOf(100000000));
            helloWorld.update("updatedMessage").send();
        } else {
            helloWorld = HelloWorld.deploy(admin, clientTransactionManager, BigInteger.valueOf(0), BigInteger.valueOf(100000000), contractRegistryAddress, "hello", "node1", "node7").send();
        }
        return helloWorld;

    }

    public ClientTransactionManager getPrivateTransactionManager(Quorum quorum, String address, List<String> nodes, int txMgrConnectionAttempts, int txMgrSleepDuration) {
        return new ClientTransactionManager(quorum, address, nodes, txMgrConnectionAttempts, txMgrSleepDuration);
    }


    public HttpService getHttpService(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(100000, TimeUnit.MILLISECONDS)
            .readTimeout(100000, TimeUnit.MILLISECONDS)
            .connectionSpecs(List.of(ConnectionSpec.CLEARTEXT))
            .build();
        return new HttpService(url, okHttpClient, false);
    }


    public ContractRegistry deployContractRegistry() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Async.defaultExecutorService();
        Admin admin = Admin.build(getHttpService("http://localhost:22000"), 100, scheduledExecutorService);
        Quorum quorum = Quorum.build(getHttpService("http://localhost:22000"));
        String address = admin.ethAccounts().send().getAccounts().get(0);
        List privateForAll = new ArrayList();
        String node1Key = "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo=";
        String node7Key = "ROAZBWtSacxXQrOe3FGAqJDyJjFePR5ce4TSIzmJ0Bc=";
        String node2Key = "QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc=";
        String node3Key = "1iTZde/ndBHvzhcl7V68x44Vx7pl8nwx9LqnM/AfJUg=";
        String node4Key = "oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8=";
        String node5Key = "R56gy4dn24YOjwyesTczYa8m5xhP6hF2uTMCju/1xkY=";
        String node6Key = "UfNSeSGySeKg11DVNEnqrUtxYRVor4+CvluI8tVv62Y=";
        //do not need node 1's private key since we are connecting to it
        privateForAll.add(node1Key);
        privateForAll.add(node2Key);
        privateForAll.add(node3Key);
        privateForAll.add(node4Key);
        privateForAll.add(node5Key);
        privateForAll.add(node6Key);
        privateForAll.add(node7Key);
        ClientTransactionManager clientTransactionManager = getPrivateTransactionManager(quorum, address, privateForAll, 100, 200);
        return ContractRegistry.deploy(admin, clientTransactionManager, BigInteger.valueOf(0), BigInteger.valueOf(100000000)).send();
    }
}

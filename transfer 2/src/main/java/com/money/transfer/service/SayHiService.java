package com.money.transfer.service;

import contracts.com.hello.world.HelloWorld;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;
import org.web3j.utils.Async;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SayHiService {

    public HelloWorld deployGreetingContract() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Async.defaultExecutorService();
        Admin admin = Admin.build(getHttpService("http://localhost:22000"), 100, scheduledExecutorService);
        Quorum quorum = Quorum.build(getHttpService("http://localhost:22000"));
        String address = admin.ethAccounts().send().getAccounts().get(0);
        String node1Key = "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo=";
        String node7Key = "ROAZBWtSacxXQrOe3FGAqJDyJjFePR5ce4TSIzmJ0Bc=";
        List list = new ArrayList();
        list.add(node1Key);
        list.add(node7Key);
        ClientTransactionManager clientTransactionManager = getPrivateTransactionManager(quorum, address, list, 40, 100);
        return HelloWorld.deploy(admin, clientTransactionManager, BigInteger.valueOf(0), BigInteger.valueOf(100000000), "hello").send();
    }

    public ClientTransactionManager getPrivateTransactionManager(Quorum quorum, String address, List<String> nodes, int txMgrConnectionAttempts, int txMgrSleepDuration) {
        return new ClientTransactionManager(quorum, address, nodes, txMgrConnectionAttempts, txMgrSleepDuration);
    }


    public HttpService getHttpService(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectionSpecs(List.of(ConnectionSpec.CLEARTEXT))
            .build();
        return new HttpService(url, okHttpClient, false);
    }

    public TransactionReceipt sayHiBack(String message, String contractAddress) throws Exception {
        ScheduledExecutorService scheduledExecutorService = Async.defaultExecutorService();
        Admin admin = Admin.build(getHttpService("http://localhost:22006"), 100, scheduledExecutorService);
        Quorum quorum = Quorum.build(getHttpService("http://localhost:22006"));
        String address = admin.ethAccounts().send().getAccounts().get(0);
        String node1Key = "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo=";
        String node7Key = "ROAZBWtSacxXQrOe3FGAqJDyJjFePR5ce4TSIzmJ0Bc=";
        List list = new ArrayList();
        list.add(node1Key);
        list.add(node7Key);
        ClientTransactionManager clientTransactionManager = getPrivateTransactionManager(quorum, address, list, 40, 100);
        HelloWorld helloWorldContract = HelloWorld.load(contractAddress, admin, clientTransactionManager, BigInteger.valueOf(0), BigInteger.valueOf(100000000));
        TransactionReceipt tx = helloWorldContract.update("hi from node 7").send();
        return tx;
    }
}

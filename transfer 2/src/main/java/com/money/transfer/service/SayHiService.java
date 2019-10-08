package com.money.transfer.service;

import contracts.com.hello.world.HelloWorld;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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

    private Map<String, String> handlerMap = new HashMap<>();

    public SayHiService() {
        handlerMap.put("0x616ea41f1d25108990ce3315d377a615ededd0a83e4cdd7fa4daafa31a71724b", "updateEvent");
        handlerMap.put("0xb81041a32a7a5e778a41739306638fbba81e6b766d123a5664a3a6bf18959912", "addEvent");
    }

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
        TransactionReceipt tx = helloWorldContract.update(message).send();
        return tx;
    }

    public void getAllNodeEvents() {
        ScheduledExecutorService scheduledExecutorService = Async.defaultExecutorService();
        Admin admin = Admin.build(getHttpService("http://localhost:22000"), 100, scheduledExecutorService);
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
            DefaultBlockParameterName.LATEST, Collections.emptyList());
        admin.ethLogFlowable(filter).subscribe(log -> {
            System.out.println("This is a log of type " + handlerMap.get(log.getTopics().get(0)));
            if (handlerMap.get(log.getTopics().get(0)).equalsIgnoreCase("addEvent")) {
                EventValues eventValues = staticExtractEventParameters(HelloWorld.ADDGREETING_EVENT, log);
                HelloWorld.AddGreetingEventResponse typedResponse = new HelloWorld.AddGreetingEventResponse();
                typedResponse.log = log;
                typedResponse.greeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
                System.out.println(typedResponse.log);
                System.out.println(typedResponse.greeting);
            } else {
                EventValues eventValues = staticExtractEventParameters(HelloWorld.UPDATEGREETING_EVENT, log);
                HelloWorld.UpdateGreetingEventResponse typedResponse = new HelloWorld.UpdateGreetingEventResponse();
                typedResponse.log = log;
                typedResponse.greeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
                System.out.println(typedResponse.log);
                System.out.println(typedResponse.greeting);
            }
            System.out.println(log.getData());
            System.out.println(log.getTransactionHash());
            System.out.println(log.getTopics());
            System.out.println(log.getBlockNumber());

        });
    }
}

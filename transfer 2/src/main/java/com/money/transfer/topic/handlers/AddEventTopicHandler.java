package com.money.transfer.topic.handlers;

import contracts.com.hello.world.HelloWorld;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventValues;
import org.web3j.protocol.core.methods.response.Log;

import static org.web3j.tx.Contract.staticExtractEventParameters;

@Component
public class AddEventTopicHandler {
    public static void handle(Log log) {
        EventValues eventValues = staticExtractEventParameters(HelloWorld.ADDGREETING_EVENT, log);
        HelloWorld.AddGreetingEventResponse typedResponse = new HelloWorld.AddGreetingEventResponse();
        typedResponse.log = log;
        typedResponse.greeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
        System.out.println(typedResponse.log);
        System.out.println(typedResponse.greeting);
        System.out.println(log.getData());
        System.out.println(log.getTransactionHash());
        System.out.println(log.getTopics());
        System.out.println(log.getBlockNumber());

    }
}

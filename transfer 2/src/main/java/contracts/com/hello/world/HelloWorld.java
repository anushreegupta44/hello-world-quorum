package contracts.com.hello.world;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class HelloWorld extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040516104e63803806104e683398101604052805101805161003a9060009060208401906100ee565b5060408051602080825260008054600260001961010060018416150201909116049183018290527fb81041a32a7a5e778a41739306638fbba81e6b766d123a5664a3a6bf18959912939092918291820190849080156100da5780601f106100af576101008083540402835291602001916100da565b820191906000526020600020905b8154815290600101906020018083116100bd57829003601f168201915b50509250505060405180910390a150610189565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061012f57805160ff191683800117855561015c565b8280016001018555821561015c579182015b8281111561015c578251825591602001919060010190610141565b5061016892915061016c565b5090565b61018691905b808211156101685760008155600101610172565b90565b61034e806101986000396000f30060806040526004361061004b5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416633d7403a38114610050578063ef690cc0146100ab575b600080fd5b34801561005c57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100a99436949293602493928401919081908401838280828437509497506101359650505050505050565b005b3480156100b757600080fd5b506100c06101f9565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100fa5781810151838201526020016100e2565b50505050905090810190601f1680156101275780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b8051610148906000906020840190610287565b5060408051602080825260008054600260001961010060018416150201909116049183018290527f616ea41f1d25108990ce3315d377a615ededd0a83e4cdd7fa4daafa31a71724b939092918291820190849080156101e85780601f106101bd576101008083540402835291602001916101e8565b820191906000526020600020905b8154815290600101906020018083116101cb57829003601f168201915b50509250505060405180910390a150565b6000805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561027f5780601f106102545761010080835404028352916020019161027f565b820191906000526020600020905b81548152906001019060200180831161026257829003601f168201915b505050505081565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102c857805160ff19168380011785556102f5565b828001600101855582156102f5579182015b828111156102f55782518255916020019190600101906102da565b50610301929150610305565b5090565b61031f91905b80821115610301576000815560010161030b565b905600a165627a7a72305820f81d6b6bf597c2eb1de5f6559fe5560d1ea108424e5c28a196fabfa4c0b2a6920029";

    public static final String FUNC_UPDATE = "update";

    public static final String FUNC_GREETING = "greeting";

    public static final Event UPDATEGREETING_EVENT = new Event("UpdateGreeting", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event ADDGREETING_EVENT = new Event("AddGreeting", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> update(String _newGreeting) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_newGreeting)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> greeting() {
        final Function function = new Function(FUNC_GREETING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public List<UpdateGreetingEventResponse> getUpdateGreetingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATEGREETING_EVENT, transactionReceipt);
        ArrayList<UpdateGreetingEventResponse> responses = new ArrayList<UpdateGreetingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UpdateGreetingEventResponse typedResponse = new UpdateGreetingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.greeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UpdateGreetingEventResponse> updateGreetingEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, UpdateGreetingEventResponse>() {
            @Override
            public UpdateGreetingEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UPDATEGREETING_EVENT, log);
                UpdateGreetingEventResponse typedResponse = new UpdateGreetingEventResponse();
                typedResponse.log = log;
                typedResponse.greeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UpdateGreetingEventResponse> updateGreetingEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATEGREETING_EVENT));
        return updateGreetingEventFlowable(filter);
    }

    public List<AddGreetingEventResponse> getAddGreetingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDGREETING_EVENT, transactionReceipt);
        ArrayList<AddGreetingEventResponse> responses = new ArrayList<AddGreetingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddGreetingEventResponse typedResponse = new AddGreetingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.greeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddGreetingEventResponse> addGreetingEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, AddGreetingEventResponse>() {
            @Override
            public AddGreetingEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDGREETING_EVENT, log);
                AddGreetingEventResponse typedResponse = new AddGreetingEventResponse();
                typedResponse.log = log;
                typedResponse.greeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddGreetingEventResponse> addGreetingEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDGREETING_EVENT));
        return addGreetingEventFlowable(filter);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _greeting) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greeting)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class UpdateGreetingEventResponse {
        public Log log;

        public String greeting;
    }

    public static class AddGreetingEventResponse {
        public Log log;

        public String greeting;
    }
}

package contracts.com.hello.world;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
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
public class ContractRegistry extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040805180820190915260018082527f310000000000000000000000000000000000000000000000000000000000000060209092019182526100539181610059565b506100f4565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061009a57805160ff19168380011785556100c7565b828001600101855582156100c7579182015b828111156100c75782518255916020019190600101906100ac565b506100d39291506100d7565b5090565b6100f191905b808211156100d357600081556001016100dd565b90565b6105c0806101036000396000f3006080604052600436106100565763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166327edd061811461005b57806335312b5f1461011b57806349061757146101a5575b600080fd5b34801561006757600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100f294369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975061023e9650505050505050565b6040805173ffffffffffffffffffffffffffffffffffffffff9092168252519081900360200190f35b34801561012757600080fd5b50610130610318565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561016a578181015183820152602001610152565b50505050905090810190601f1680156101975780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101b157600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261023c94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506103a59650505050505050565b005b600080836040518082805190602001908083835b602083106102715780518252601f199092019160209182019101610252565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842086519094879450925082918401908083835b602083106102cd5780518252601f1990920191602091820191016102ae565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205473ffffffffffffffffffffffffffffffffffffffff1695945050505050565b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561039d5780601f106103725761010080835404028352916020019161039d565b820191906000526020600020905b81548152906001019060200180831161038057829003601f168201915b505050505081565b336000836040518082805190602001908083835b602083106103d85780518252601f1990920191602091820191016103b9565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842086519094879450925082918401908083835b602083106104345780518252601f199092019160209182019101610415565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381018420805473ffffffffffffffffffffffffffffffffffffffff191673ffffffffffffffffffffffffffffffffffffffff9690961695909517909455505082513392600092859290918291908401908083835b602083106104ce5780518252601f1990920191602091820191016104af565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101842087519094889450925082918401908083835b6020831061052a5780518252601f19909201916020918201910161050b565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805473ffffffffffffffffffffffffffffffffffffffff191673ffffffffffffffffffffffffffffffffffffffff9490941693909317909255505050505600a165627a7a723058203291481a3f00efd83bce01e6c4f4681dd7625a321186f225c35be465105b90260029";

    public static final String FUNC_GETCONTRACT = "getContract";

    public static final String FUNC_CONTRACTREGISTRYVERSION = "contractRegistryVersion";

    public static final String FUNC_ADDCONTRACT = "addContract";

    @Deprecated
    protected ContractRegistry(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ContractRegistry(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ContractRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ContractRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getContract(String _sender, String _receiver) {
        final Function function = new Function(FUNC_GETCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_sender), 
                new org.web3j.abi.datatypes.Utf8String(_receiver)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> contractRegistryVersion() {
        final Function function = new Function(FUNC_CONTRACTREGISTRYVERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addContract(String _sender, String _receiver) {
        final Function function = new Function(
                FUNC_ADDCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_sender), 
                new org.web3j.abi.datatypes.Utf8String(_receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ContractRegistry load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ContractRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ContractRegistry load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ContractRegistry(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ContractRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ContractRegistry(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ContractRegistry> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractRegistry.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<ContractRegistry> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractRegistry.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractRegistry> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractRegistry.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractRegistry> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractRegistry.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}

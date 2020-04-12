package com.pzz.review.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
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
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class ReviewContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610934806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063175a3b4114610051578063990581b614610105578063e83ddcea146101b6578063efb90cc8146101d3575b600080fd5b6101036004803603606081101561006757600080fd5b81359160208101359181019060608101604082013564010000000081111561008e57600080fd5b8201836020820111156100a057600080fd5b803590602001918460018302840111640100000000831117156100c257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610285945050505050565b005b6101226004803603602081101561011b57600080fd5b50356103d7565b6040518086815260200185815260200180602001848152602001838152602001828103825285818151815260200191508051906020019080838360005b8381101561017757818101518382015260200161015f565b50505050905090810190601f1680156101a45780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b610122600480360360208110156101cc57600080fd5b5035610590565b610103600480360360608110156101e957600080fd5b81359160208101359181019060608101604082013564010000000081111561021057600080fd5b82018360208201111561022257600080fd5b8035906020019184600183028401116401000000008311171561024457600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061066b945050505050565b600080548482526001602052604090912054106102e757604080516000815260208101829052600e818301526d4e6f20737563682072657669657760901b606082015290516000805160206108df8339815191529181900360800190a16103d2565b600083815260016020526040812054815484929190811061030457fe5b90600052602060002090600502016001018190555080600060016000868152602001908152602001600020548154811061033a57fe5b9060005260206000209060050201600201908051906020019061035e929190610814565b50600083815260016020526040812054815442929190811061037c57fe5b6000918252602091829020600460059092020101919091556040805160018152918201819052600782820152665375636365737360c81b6060830152516000805160206108df8339815191529181900360800190a15b505050565b6000805482825260016020526040822054829160609183918291106103fb57600080fd5b6000868152600160205260408120548154811061041457fe5b906000526020600020906005020160000154600060016000898152602001908152602001600020548154811061044657fe5b9060005260206000209060050201600101546000600160008a8152602001908152602001600020548154811061047857fe5b90600052602060002090600502016002016000600160008b815260200190815260200160002054815481106104a957fe5b9060005260206000209060050201600301546000600160008c815260200190815260200160002054815481106104db57fe5b600091825260209182902060046005909202010154835460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815291928591908301828280156105765780601f1061054b57610100808354040283529160200191610576565b820191906000526020600020905b81548152906001019060200180831161055957829003601f168201915b505050505092509450945094509450945091939590929450565b6000818154811061059d57fe5b9060005260206000209060050201600091509050806000015490806001015490806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106555780601f1061062a57610100808354040283529160200191610655565b820191906000526020600020905b81548152906001019060200180831161063857829003601f168201915b5050505050908060030154908060040154905085565b60008054848252600160205260409091205410156106cd576040805160008152602081018290526011818301527012185d99481899595b88195e1a5cdd1959607a1b606082015290516000805160206108df8339815191529181900360800190a15b6106d5610892565b506040805160a0810182528481526020808201858152928201848152426060840181905260808401526000805460018101825590805283517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563600590920291820190815594517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e56482015590518051939485949093610797937f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e56501920190610814565b506060828101516003830155608092830151600490920191909155600080548451825260016020818152604093849020600019939093019092558251908152908101829052600781830152665375636365737360c81b92810192909252516000805160206108df833981519152929181900390910190a150505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061085557805160ff1916838001178555610882565b82800160010185558215610882579182015b82811115610882578251825591602001919060010190610867565b5061088e9291506108c1565b5090565b6040518060a0016040528060008152602001600081526020016060815260200160008152602001600081525090565b6108db91905b8082111561088e57600081556001016108c7565b9056fe649d11cf246fc1a31ca3c4a69ad2bb6d5af11a696f732dc2ae11ccc062e849aea264697066735822122015d355a74f2286c52514b6f6cd1f04f03479bd43aae65665078d4a645be25bd564736f6c63430006040033";

    public static final String FUNC_GETREVIEW = "getReview";

    public static final String FUNC_INSERTREVIEW = "insertReview";

    public static final String FUNC_REVIEWS = "reviews";

    public static final String FUNC_UPDATEREVIEW = "updateReview";

    public static final Event RESPONSE_EVENT = new Event("Response", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ReviewContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ReviewContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ReviewContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ReviewContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ResponseEventResponse> getResponseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RESPONSE_EVENT, transactionReceipt);
        ArrayList<ResponseEventResponse> responses = new ArrayList<ResponseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ResponseEventResponse typedResponse = new ResponseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ResponseEventResponse> responseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ResponseEventResponse>() {
            @Override
            public ResponseEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RESPONSE_EVENT, log);
                ResponseEventResponse typedResponse = new ResponseEventResponse();
                typedResponse.log = log;
                typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.message = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ResponseEventResponse> responseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESPONSE_EVENT));
        return responseEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> getReview(BigInteger project_id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETREVIEW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(project_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> insertReview(BigInteger project_id, BigInteger status, String opinion) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INSERTREVIEW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(project_id), 
                new org.web3j.abi.datatypes.generated.Uint256(status), 
                new org.web3j.abi.datatypes.Utf8String(opinion)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reviews(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVIEWS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateReview(BigInteger project_id, BigInteger status, String opinion) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEREVIEW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(project_id), 
                new org.web3j.abi.datatypes.generated.Uint256(status), 
                new org.web3j.abi.datatypes.Utf8String(opinion)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ReviewContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ReviewContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ReviewContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ReviewContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ReviewContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ReviewContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ReviewContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ReviewContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ReviewContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ReviewContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ReviewContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ReviewContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ReviewContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ReviewContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ReviewContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ReviewContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ResponseEventResponse extends BaseEventResponse {
        public BigInteger code;

        public String message;
    }
}

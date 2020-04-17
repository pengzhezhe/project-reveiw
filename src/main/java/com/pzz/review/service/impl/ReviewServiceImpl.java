package com.pzz.review.service.impl;

import com.pzz.review.contract.ReviewContract;
import com.pzz.review.domain.Review;
import com.pzz.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials credentials;

    @Value("${web3j.contract.address}")
    private String contractAddress;

    @Value("${web3j.account.address}")
    private String address;

    @Override
    public ReviewContract deployContract() {
        try {
            ReviewContract reviewContract = ReviewContract.deploy(web3j, credentials, new DefaultGasProvider()).send();
            log.info("合约部署成功，合约地址:" + reviewContract.getContractAddress());
            return reviewContract;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Deploy contract error");
        }
    }

    @Override
    public ReviewContract loadContract(String contractAddress) {
        return ReviewContract.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    @Override
    public Review getReview(BigInteger projectId) {
        Review review = new Review();
        Function function = new Function("getReview",
                Arrays.asList(new Uint(projectId)),
                Arrays.asList(new TypeReference<Uint>() {
                              },
                        new TypeReference<Uint>() {
                        },
                        new TypeReference<Utf8String>() {
                        },
                        new TypeReference<Uint>() {
                        },
                        new TypeReference<Uint>() {
                        }
                )
        );
        String encodedFunction = FunctionEncoder.encode(function);
        try {
            EthCall response = web3j.ethCall(Transaction.createEthCallTransaction(address, contractAddress, encodedFunction), DefaultBlockParameterName.LATEST).sendAsync().get();
            List<Type> output = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
            Type<BigInteger> project_Id = output.get(0);
            Type<BigInteger> status = output.get(1);
            Type<String> opinion = output.get(2);
            Type<BigInteger> createTime = output.get(3);
            Type<BigInteger> updateTime = output.get(4);
            log.info(createTime.getValue().toString());
            review.setProjectId(project_Id.getValue().intValue());
            review.setStatus(status.getValue().intValue());
            review.setOpinion(opinion.getValue());
            review.setCreateTime(new Timestamp(createTime.getValue().longValue() * 1000));
            review.setUpdateTime(new Timestamp(updateTime.getValue().longValue() * 1000));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public boolean insertReview(BigInteger projectId, BigInteger status, String opinion) {
        ReviewContract reviewContract = loadContract(contractAddress);
        try {
            TransactionReceipt transactionReceipt = reviewContract.insertReview(projectId, status, opinion).send();
            List<ReviewContract.ResponseEventResponse> responseEvents = reviewContract.getResponseEvents(transactionReceipt);
            if (responseEvents.get(0).code.equals(BigInteger.ONE))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReview(BigInteger projectId, BigInteger status, String opinion) {
        ReviewContract reviewContract = loadContract(contractAddress);
        try {
            TransactionReceipt transactionReceipt = reviewContract.updateReview(projectId, status, opinion).send();
            List<ReviewContract.ResponseEventResponse> responseEvents = reviewContract.getResponseEvents(transactionReceipt);
            if (responseEvents.get(0).code.equals(BigInteger.ONE))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

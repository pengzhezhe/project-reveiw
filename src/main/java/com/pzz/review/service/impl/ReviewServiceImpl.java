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
        //定义函数
        Function function = new Function("getReview",
                //定义函数参数
                Arrays.asList(new Uint(projectId)),
                //定义函数返回值
                Arrays.asList(
                        new TypeReference<Uint>() {
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
            //调用函数
            EthCall response = web3j.ethCall(Transaction.createEthCallTransaction(address, contractAddress, encodedFunction), DefaultBlockParameterName.LATEST).sendAsync().get();
            //获取返回值
            List<Type> output = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
            Uint project_id = (Uint) output.get(0);
            Uint status = (Uint) output.get(1);
            Utf8String opinion = (Utf8String) output.get(2);
            Uint createTime = (Uint) output.get(3);
            Uint updateTime = (Uint) output.get(4);
            review.setProjectId(project_id.getValue().intValue());
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
            //向区块链发送交易
            TransactionReceipt transactionReceipt = reviewContract.insertReview(projectId, status, opinion).send();
            //获取事件返回值
            List<ReviewContract.ResponseEventResponse> responseEvents = reviewContract.getResponseEvents(transactionReceipt);
            ReviewContract.ResponseEventResponse response = responseEvents.get(0);
            log.info(response.code.toString());
            log.info(response.message);
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

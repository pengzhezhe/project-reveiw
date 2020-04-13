package com.pzz.review.service;

import com.pzz.review.contract.ReviewContract;
import com.pzz.review.domain.Review;

import java.math.BigInteger;

public interface ReviewService {

    /**
     * 部署合约
     *
     * @return 合约
     */
    ReviewContract deployContract();

    /**
     * 加载合约
     *
     * @param contractAddress 合约地址
     * @return 合约
     */
    ReviewContract loadContract(String contractAddress);

    /**
     * 获取项目评审记录
     *
     * @param projectId 项目Id
     * @return 评审记录
     */
    Review getReview(BigInteger projectId);

    /**
     * 添加评审记录
     *
     * @param projectId 项目Id
     * @param status    评审状态，1 未通过 2 通过
     * @param opinion   评审意见
     * @return boolean
     */
    boolean insertReview(BigInteger projectId, BigInteger status, String opinion);

    /**
     * 更新评审记录
     *
     * @param projectId 项目Id
     * @param status    评审状态，1 未通过 2 通过
     * @param opinion   评审意见
     * @return boolean
     */
    boolean updateReview(BigInteger projectId, BigInteger status, String opinion);
}

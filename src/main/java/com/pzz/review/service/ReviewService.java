package com.pzz.review.service;

import com.pzz.review.contract.ReviewContract;
import com.pzz.review.domain.Review;

import java.math.BigInteger;

public interface ReviewService {

    ReviewContract deployContract();

    ReviewContract loadContract(String contractAddress);

    Review getReview(BigInteger projectId);

    boolean insertReview(BigInteger projectId, BigInteger status,String opinion);

    boolean updateReview(BigInteger projectId, BigInteger status,String opinion);
}

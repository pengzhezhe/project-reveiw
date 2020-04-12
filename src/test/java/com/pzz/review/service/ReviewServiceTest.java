package com.pzz.review.service;

import com.pzz.review.contract.ReviewContract;
import com.pzz.review.domain.Review;
import com.pzz.review.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Value("${web3j.contract.address}")
    private String contractAddress;

    @Value("${web3j.account.address}")
    private String address;

    @Test
    public void deployContract() {
        ReviewContract reviewContract = reviewService.deployContract();
        System.out.println(reviewContract.getContractAddress());
    }

    @Test
    public void loadContract() throws IOException {
        ReviewContract reviewContract = reviewService.loadContract(contractAddress);
        boolean valid = reviewContract.isValid();
        System.out.println(valid);
    }

    @Test
    public void getReview() {
        Review review = reviewService.getReview(BigInteger.ONE);
        System.out.println(review);
    }

    @Test
    public void insertReview() {
        boolean b = reviewService.insertReview(BigInteger.ONE, BigInteger.ZERO, "123456");
        System.out.println(b);
    }

    @Test
    public void updateReview() {
        boolean b = reviewService.updateReview(BigInteger.ONE, BigInteger.ONE, "654321");
        System.out.println(b);
        Review review = reviewService.getReview(BigInteger.ONE);
        System.out.println(review);
    }

    @Test
    public void test() {
        String str="计算机学院关于2020届本科毕业设计（论文）检测和答辩的通知安排.pdf";
        String substring = str.substring(str.lastIndexOf("."));
        System.out.println(substring);
    }
}
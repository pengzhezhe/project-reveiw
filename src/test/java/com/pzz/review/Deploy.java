package com.pzz.review;

import com.pzz.review.contract.ReviewContract;
import com.pzz.review.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Deploy {
    @Autowired
    private ReviewService reviewService;

    @Test
    public void deployContract() {
        ReviewContract reviewContract = reviewService.deployContract();
    }
}

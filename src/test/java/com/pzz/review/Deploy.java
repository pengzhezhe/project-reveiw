package com.pzz.review;

import com.pzz.review.contract.ReviewContract;
import com.pzz.review.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Deploy {
    @Autowired
    private ReviewService reviewService;

    @Test
    public void deployContract() {
        ReviewContract reviewContract = reviewService.deployContract();
    }
}

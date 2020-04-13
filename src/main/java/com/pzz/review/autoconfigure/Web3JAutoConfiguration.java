package com.pzz.review.autoconfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

/**
 * Web3J自动配置
 */
@Configuration
public class Web3JAutoConfiguration {

    /**
     * 区块链管理员账户钱包文件路径
     */
    @Value("${web3j.account.keypath}")
    private String keyPath;

    /**
     * 区块链管理员账户密码
     */
    @Value("${web3j.account.password}")
    private String password;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService());
    }

    @Bean
    public Admin admin() {
        return Admin.build(new HttpService());
    }

    @Bean
    public Credentials credentials() {
        try {
            return WalletUtils.loadCredentials(password, keyPath);
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Load Wallet File Error");
    }
}

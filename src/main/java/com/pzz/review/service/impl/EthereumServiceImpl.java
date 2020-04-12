package com.pzz.review.service.impl;

import com.pzz.review.service.EthereumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Slf4j
public class EthereumServiceImpl implements EthereumService {
    @Autowired
    private Admin admin;

    @Value("${web3j.account.keypath}")
    private String keyPath;

    @Override
    public String newAccount(String password) {
        try {
            return admin.personalNewAccount(password).send().getAccountId();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

# 基于以太坊的项目评审系统

## 运行环境
操作系统：Windows10 

Java：JDK11

Java IDE:IntelliJ IDEA 2020.1

MySQl：MySQL5.7

以太坊客户端: Geth1.9.10

## 部署过程
1. 安装运行环境所需软件。
2. 运行项目目录下script中的project_review.sql脚本。
3. 打开命令行切换到项目目录script目录下，运行 geth init genesis.json 命令，初始化以太坊区块链。
4. 运行 geth --nodiscover --rpc --rpcapi eth,web3,personal --allow-insecure-unlock console 启动以太坊。在控制台输入personal.newAccount("123456").记录下key file的路径和账户地址。在控制台输入miner.start(1)开始挖矿(不要关闭此界面，可能会很卡)。
5. 使用IDEA导入项目。
6. 修改src/main/resources/application.properties中的数据源配置，修改web3j.account.address值为第四步的账户地址，修改web3j.account.keypath为第四步的key file路径。
7. 运行src/test/java/com/pzz/review下的Deploy.java，并将控制台输出的合约地址复制到application.properties中的web3j.contract.address中。
8. 运行src/main/java/com/pzz/review/ProjectReviewApplication.java 启动SpringBoot项目。
9. 在浏览器中输入localhost:8080/login进入登录界面(默认用户：pengzhezhe，密码：123456)(默认管理员：admin，密码:123456)
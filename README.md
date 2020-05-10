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

## 功能模块

用户子模块

- 用户登录注册
- 个人信息管理
- 发布项目
- 查看项目
- 查看公告

管理员子模块

- 管理员登录
- 个人信息管理
- 用户管理
- 公告管理
- 项目管理
- 项目审核

## 数据库设计

### User

用户信息表

|   字段名    |  数据类型   | 非空 |             描述             |
| :---------: | :---------: | :--: | :--------------------------: |
|     id      |   int(11)   |  是  |             主键             |
|  username   | varchar(20) |  是  |            用户名            |
|  password   | varchar(20) |  是  |             密码             |
|    name     | varchar(10) |  是  |             姓名             |
|     sex     |   int(11)   |  是  |             性别             |
|    email    | varchar(20) |  是  |             邮箱             |
|  user_type  |   int(11)   |  是  | 用户类型，0普通用户，1管理员 |
| create_time |  timestamp  |  是  |           创建时间           |
| update_time |  timestamp  |  是  |           更新时间           |




### Project

项目信息表

|    字段名    |   数据类型   | 非空 |            描述            |
| :----------: | :----------: | :--: | :------------------------: |
|      id      |   int(11)    |  是  |            主键            |
|     name     | varchar(30)  |  是  |           项目名           |
| introduction | varchar(255) |  是  |          项目描述          |
|   user_id    |   int(11)    |  是  |      项目发起人，外键      |
|  user_name   | varchar(10)  |  是  |       项目发起人姓名       |
|    status    |   int(11)    |  是  | 项目状态 0 待审核 1 已审核 |
| create_time  |  timestamp   |  是  |          创建时间          |
| update_time  |  timestamp   |  是  |          更新时间          |

### Attachment

附件信息

|    字段名     |   数据类型   | 非空 |      描述      |
| :-----------: | :----------: | :--: | :------------: |
|      id       |   int(11)    |  是  |      主键      |
|  project_id   |   int(11)    |  是  | 所属项目，外键 |
| original_name | varchar(100) |  是  |   附件原始名   |
|   filename    | varchar(50)  |  是  |   附件文件名   |
|  create_time  |  timestamp   |  是  |    创建时间    |
|  update_time  |  timestamp   |  是  |    更新时间    |


### Announcement

公告信息表

|   字段名    |   数据类型   | 非空 |   描述   |
| :---------: | :----------: | :--: | :------: |
|     id      |   int(11)    |  是  |   主键   |
|    title    | varchar(20)  |  是  | 公告标题 |
|   content   | varchar(100) |  是  | 公告内容 |
| create_time |  timestamp   |  是  | 创建时间 |
| update_time |  timestamp   |  是  | 更新时间 |



## 区块链设计

| **字段名**  | **数据类型** |          **描述**          |
| :---------: | :----------: | :------------------------: |
|     id      |     uint     |           项目id           |
|   status    |     uint     | 评审结果 1 未通过 、2 通过 |
|   opinion   |    string    |          评审意见          |
| review_time |     uint     |     评审时间（时间戳）     |

```solidity
struct Review{
    uint project_id;
    int status;
    uint create_time;
    uint update_time;
}
```


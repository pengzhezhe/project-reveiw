del Review.abi
del Review.bin
call solcjs Review.sol --bin --abi --optimize -o ./
ren Review_sol_Review.abi Review.abi
ren Review_sol_Review.bin Review.bin
del ../../java/com/pzz/review/contract/ReviewContract.java
call web3j solidity generate -b Review.bin -a Review.abi -o ../../java -p com.pzz.review.contract -c ReviewContract
package org.kakaobank.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.kakaobank.transaction.domain.*;
import org.kakaobank.repository.Account;
import org.kakaobank.repository.AccountRepository;
import org.kakaobank.repository.User;
import org.kakaobank.repository.UserRepository;
import org.kakaobank.evalutation.domain.Transfer;
import org.kakaobank.utils.Utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Random;

public final class CreateLog {
    //    private static Long uniqueUserId = 0L;
    private static long minAmount = 1;
    private static long maxAmount = 1000000;
    private ObjectMapper objectMapper;
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private static final String BANKNAME = "KAKAOBANK";

    public CreateLog() {
        this.objectMapper = new ObjectMapper();
        this.accountRepository = new AccountRepository();
        this.userRepository = new UserRepository();
    }

    public Long getUniqueUserId() {
        Long userCount = userRepository.getUserCount();
        return userCount;
    }

    //Signup Log
    public String createSignup() {
        String signupLog;
        User user = new User("morris", Utils.createBirthDay());
        userRepository.saveUser(user);
        LocalDateTime now = LocalDateTime.now();
        Signup signup = new Signup(getUniqueUserId(), user.getUsername(), user.getBirthday(), now);
        try {
            signupLog = objectMapper.writeValueAsString(signup);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("create SignupTransaction Error");
        }

        return signupLog;
    }

    //계좌 생성
    public String creatAccountOpenTransaction(Long userId) {
        String result = "";
        String accountNumber = Utils.creatAccountNumber();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        accountRepository.addTransactionAmount(userId, new Account(accountNumber, BigDecimal.ZERO));
        AccountOpen accountOpen = new AccountOpen(userId, accountNumber, LocalDateTime.now());
        try {
            result = objectMapper.writeValueAsString(accountOpen);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("craete AccountOpenTranscation Error");
        }
        return result;
    }

    //예금
    public String createDeposit() {
        String result = "";
        long userid = getRandomID();
        Account user = accountRepository.getAccountByUserId(userid);
        BigDecimal depositMoney = Utils.makeDepositMoney(minAmount, maxAmount);
        //save
        accountRepository.updateDepositTransaction(userid, depositMoney);
        //log
        Deposit deposit = new Deposit(userid, user.getAccountNumber(), depositMoney, LocalDateTime.now());

        try {
            result = objectMapper.writeValueAsString(deposit);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("craete DepositTranscation Error");
        }

        return result;
    }

    //출금
    public String createWithdraw() {
        String result = "";
        long userid = getRandomID();
        Withdraw withdraw;
        Account user = accountRepository.getAccountByUserId(userid);
        if (user.getAmount().equals(BigDecimal.ZERO) ||
                minAmount == user.getAmount().longValue()) {
            return "null";
        }
        BigDecimal withdrawMoney = Utils.makeWithdrawMoney(user.getAmount(), minAmount, user.getAmount().longValue());
        boolean isExecuteTransaction = accountRepository.updateWithdrawTransaction(userid, withdrawMoney);

        if (isExecuteTransaction) {
            withdraw = new Withdraw(userid, user.getAccountNumber(), withdrawMoney, LocalDateTime.now());
            try {
                result = objectMapper.writeValueAsString(withdraw);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException("craete WithdrawTranscation Error");
            }
        }
        return result;
    }

    //Transfer
    public String createTransfer() {
        String result = "";
        /*
         * 이체로그 : Transfer
         *
         * 고객번호 : userid
         * 송금계좌번호 : remittanceAccountNumber
         * 수취은행 : receiptBankName
         * 수취 계좌번호 : receiptAccountNumber
         * 수취계좌주 : receiptUserName
         * 이체금액 : amount
         * 거래시각 : transcationTime
         */

        long sender = getRandomID();
        Account senderAccount = accountRepository.getAccountByUserId(sender);
        Long receiverId = getNotDuplicateID(sender);
        if (senderAccount.getAmount().equals(BigDecimal.ZERO)) {
            return "null";
        }
        boolean present = Optional.of(accountRepository.getAccountByUserId(receiverId)).isPresent();
        if (present == false) {
            return "null";
        }
        Account receiverAccount = accountRepository.getAccountByUserId(receiverId);
        User receiverUser = userRepository.getUserByUserID(receiverId);
        BigDecimal sendMoneyAmount = makeTransferMoney(1, senderAccount.getAmount().longValue());
        System.out.println("receiverUser : " + receiverId);
        Transfer transfer = new Transfer(sender,
                senderAccount.getAccountNumber(),
                BANKNAME,
                receiverAccount.getAccountNumber(),
                receiverUser.getUsername(),
                sendMoneyAmount,
                LocalDateTime.now());
        accountRepository.updateTransfer(transfer, receiverId);

        try {
            result = objectMapper.writeValueAsString(transfer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("craete WithdrawTranscation Error");
        }
        System.out.println("end Transfer");
        return result;
    }

    public Long getNotDuplicateID(Long sender) {
        boolean isDuplicate = true;
        long receiverUserID = getRandomID();
        while (isDuplicate) {
            if (sender != receiverUserID) {
                isDuplicate = false;
            } else {
                receiverUserID = getRandomID();
            }
        }
        return receiverUserID;
    }


    //등록된 유저중 랜덤 아이디
    public Long getRandomID() {
        Random random = new Random();
        OptionalLong first = random.longs(1, (getUniqueUserId() + 1)).findFirst();
        return first.getAsLong();
    }

    //금액 송금 체크 : 계좌 보유금액금 내 금액
    public BigDecimal makeTransferMoney(long minAmount, long maxAmount) {
        Random random = new Random();
        BigDecimal withdrawAmount = null;
        long randomMoney = random.longs(minAmount, maxAmount+1).findFirst().getAsLong();
        withdrawAmount = new BigDecimal(randomMoney);
        return withdrawAmount;
    }
}

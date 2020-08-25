package org.kakaobank.repository;


import org.kakaobank.transaction.domain.Transfer;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository {
    private Map<Long,Account> accountRepository = new ConcurrentHashMap();


    public Account getAccountByUserId(Long userid){
        Account account ;
        if(accountRepository.containsKey(userid)){
            account = accountRepository.get(userid);
        }else{
            throw new RuntimeException(" 계좌가 없습니다.");
        }
        return account;
    }
    public void getAccountCheck( Long userid){
        if(accountRepository.containsKey(userid)){
            throw new RuntimeException("유저 아이디가 중복됩니다.");
        }
    }

    //create
    public void addTransactionAmount( Long userid,Account account){
        getAccountCheck( userid);
//        account.setAmount( BigDecimal.ZERO);
        accountRepository.put( userid,account);
//        System.out.println( "user : "+userid+" 계좌 생성 완료");
    }

    public boolean updateDepositTransaction( Long userid,BigDecimal amount){
        boolean isExecute ;
        if(accountRepository.containsKey(userid)){
            Account account = accountRepository.get(userid);
            BigDecimal currentAmount = account.getAmount();
            BigDecimal updateAmount = currentAmount.add(amount);
            account.setAmount(updateAmount);
            accountRepository.put( userid,account);
            isExecute=true;
        }else{
            isExecute=false;
        }


        return isExecute;
    }

    public boolean updateWithdrawTransaction(Long userid,BigDecimal amount){
        boolean isExecute ;
        Account account = accountRepository.get(userid);
        BigDecimal currentAmount = account.getAmount();
        BigDecimal updateAmount = currentAmount.subtract(amount) ;
        account.setAmount(updateAmount);
        if(updateAmount.compareTo(BigDecimal.ZERO)<0){
            System.out.println("잔액이 부족합니다.");
            isExecute=false;
        }else{
            accountRepository.put(userid,account);
            isExecute=true;
        }
        return isExecute;
    }

    public void updateTransfer(Transfer transfer, Long receiverId) {
        //senderTransaction
        Long sender = transfer.getSenderId();
        BigDecimal sendAmount = transfer.getSendAmount();
        Account senderAccount = accountRepository.get(sender);
        BigDecimal afterTransferAmount = senderAccount
                .getAmount().subtract(sendAmount);
        if(afterTransferAmount.compareTo(BigDecimal.ZERO)<0){
            System.out.println("잔액이 부족합니다");
        }else{
            senderAccount.setAmount(afterTransferAmount);
            accountRepository.put(sender,senderAccount);
            //receiverTransaction
            Account receiverAccount = accountRepository.get(receiverId);
            BigDecimal afterReceiveMoney = receiverAccount.getAmount().add(sendAmount);
            receiverAccount.setAmount( afterReceiveMoney);
            accountRepository.put(receiverId,receiverAccount);
        }


    }
}

package org.kakaobank.module;

import org.kakaobank.evalutation.domain.*;
import org.kakaobank.profile.OlderProfile;
import org.kakaobank.profile.UserProfile;
import org.kakaobank.repository.OlderRepository;
import org.kakaobank.repository.TempRepository;
import org.kakaobank.repository.UserProfileRepository;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class FDSdetection {
    private final int detectionAgeLine = 60;
    private final int transactionWithInTime = 5;
    private final int registerWithTnTime = 48;
    private final BigDecimal limitDetectionAmount = BigDecimal.valueOf(100000);
    private TempRepository olderRepository;
    private UserProfileRepository userProfileRepository;

    public FDSdetection(){
        this.olderRepository = new TempRepository();
        userProfileRepository = new UserProfileRepository();
    }

    public void detectFDS(Log log){
        if(log instanceof Signup) {
            Signup signup = (Signup) log;
        }else if(log instanceof AccountOpen){
            AccountOpen accountOpen = (AccountOpen)log;
            ageDetection(accountOpen);
        }else if(log instanceof Withdraw){
            Withdraw withdraw = (Withdraw) log;
            withdrawDetect(withdraw);
        }else if(log instanceof Deposit){
             Deposit deposit = (Deposit) log;
             executedeposit(deposit);
        }else if(log instanceof Transfer){
            Transfer transfer = (Transfer) log;
            transferDetection(transfer);
        }
    }

    private void transferDetection(Transfer transfer) {
        Long senderId = transfer.getSenderId();
        String receiptAccountNumber = transfer.getReceiptAccountNumber();
        //보내는 사람 송금시 계좌 확인후 detect
        Optional<OlderProfile> user = olderRepository.findByUserID(senderId);
        if(user.isPresent()){
            OlderProfile olderProfile = user.get();
            Optional<Timestamp> openAccountTime = Optional.of(olderProfile.getOpenAccountTime());
            //계좌 등록 시간이 48시가 이내이면서 100만원 이상을 저장했던 경우
            if(compareTime(olderProfile.getRegisterTime(),registerWithTnTime) && openAccountTime.isPresent()){
                UserProfile userProfile = userProfileRepository.findbyUserId(senderId).get();
                BigDecimal updateAmount = userProfile.getAmount().subtract(transfer.getSendAmount());
                userProfile.setAmount(updateAmount);
                int compareAfterTransfer = updateAmount.compareTo(limitDetectionAmount);
                //잔액이 10000원 이하인경우
                if(compareAfterTransfer<=0){
                    //2시간 이내일경우
                    System.out.println(" Detect transfer : "+ olderProfile.getOverAmountTime()+","+transactionWithInTime);
                    if(compareTime(olderProfile.getOverAmountTime(),transactionWithInTime)){
                        System.out.println("Find Detection");
                        System.out.println(transfer);
                        System.out.println(userProfile);
                    }
                }
            };
        }

        //receiveAccount가 100만원 이상일 경우 추가 olderRepository update
        Optional<UserProfile> userProfile = userProfileRepository.findbyUserAccountNumber(receiptAccountNumber);
        if(userProfile.isPresent()){
            UserProfile userProfile1 = userProfile.get();
            Long userid = userProfile1.getUserid();
            Optional<OlderProfile> olderProfile = olderRepository.findByUserID(userid);
            if(olderProfile.isPresent()){
                OlderProfile olderProfile1 = olderProfile.get();
                Optional<Timestamp> overAmountTime = Optional.of(olderProfile1.getOverAmountTime());
                BigDecimal updateAmount = userProfile1.getAmount().add(transfer.getSendAmount());
                int compareTo = updateAmount.compareTo(limitDetectionAmount);
                if(compareTo>=0&&overAmountTime.isPresent()){
                    olderProfile1.setOpenAccountTime(Timestamp.valueOf(LocalDateTime.now()));
                    olderRepository.save(userid,olderProfile1);
                }
            }
        }
    }

    private void executedeposit(Deposit deposit) {
        //100만원 이상일 경우 추가 OrderProfile에 추가 하기
        long userid = deposit.getUserid();
        Optional<OlderProfile> user = olderRepository.findByUserID(userid);
        if(user.isPresent()){
            UserProfile userProfile = userProfileRepository.findbyUserId(userid).get();
            OlderProfile olderProfile = user.get();
            BigDecimal addAmountMoney = userProfile.getAmount().add(deposit.getAmount());
            int compareTo = addAmountMoney.compareTo(limitDetectionAmount);
            if(compareTo>=0){
                olderProfile.setOpenAccountTime(Timestamp.valueOf(LocalDateTime.now()));
                olderRepository.save(userid,olderProfile);
            }
        }
    }

    private void withdrawDetect(Withdraw withdraw) {
        Long userid = withdraw.getUserid();
        Optional<OlderProfile> user = olderRepository.findByUserID(userid);
        //60세 이상이라면
        if(user.isPresent()){
            OlderProfile olderProfile = user.get();
            Optional<Timestamp> openAccountTime = Optional.of(olderProfile.getOpenAccountTime());
            //계좌 등록 시간이 48시가 이내이면서 100만원 이상을 저장했던 경우
            if(compareTime(olderProfile.getRegisterTime(),registerWithTnTime) && openAccountTime.isPresent()){
                UserProfile userProfile = userProfileRepository.findbyUserId(userid).get();
                BigDecimal updateAmount = userProfile.getAmount().subtract(withdraw.getAmount());
                userProfile.setAmount(updateAmount);
                int i = updateAmount.compareTo(limitDetectionAmount);
                //잔액이 10000원 이하인경우
                if(i<=0){
                    //2시간 이내일경우
                    if(compareTime(olderProfile.getOverAmountTime(),transactionWithInTime)){
                        System.out.println("Find Detection");
                        System.out.println(withdraw);
                        System.out.println(userProfile);
                    }
                }
            };

        }
    }

//    private boolean compareTime(Timestamp timestamp, int limitTime) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime dateTime = timestamp.toLocalDateTime();
//        Duration diff = Duration.between(now,dateTime);
//        return limitTime > diff.toHours();
//    }
    //TODO Test
    private boolean compareTime(Timestamp timestamp, int limitTime) {
        Timestamp recordTime = timestamp;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime = recordTime.toLocalDateTime();
        Duration diff = Duration.between(now,dateTime);
        return limitTime > diff.toMinutes();
    }

    private void ageDetection(AccountOpen accountOpen) {
        Timestamp openAccountTime = accountOpen.getOpenAccountTime();
        Optional<UserProfile> userProfile = userProfileRepository.findbyUserId(accountOpen.getUserid());
        if(userProfile.isPresent()){
            UserProfile user = userProfile.get();
            if(checkIsOlderAge(user.getBirthday())){
                Long userid = user.getUserid();
                Timestamp registerTime = user.getRegisterTime();
                olderRepository.save( userid ,new OlderProfile(userid,registerTime,openAccountTime));
            }
        }
    }

    private boolean checkIsOlderAge(Timestamp birthday){

        Calendar date = Calendar.getInstance();
        date.setTime(birthday);
        int birthOfYear = date.get(Calendar.YEAR);
        int age = LocalDate.now().getYear() - birthOfYear+1;
        return age>=detectionAgeLine;
    }
}

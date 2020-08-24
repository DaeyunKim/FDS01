package org.kakaobank.module;

import org.kakaobank.evalutation.domain.*;
import org.kakaobank.profile.UserProfile;
import org.kakaobank.repository.UserProfileRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class UserProfileService {
    private UserProfileRepository userProfileRepository;
    private FDSdetection fdsdetection;
    public UserProfileService() {
        this.userProfileRepository = new UserProfileRepository();
        this.fdsdetection = new FDSdetection();
    }

    public void getTransactionLog(Log log){
        if(log instanceof Signup) {
            saveUserProfile((Signup) log);
        }else if(log instanceof AccountOpen){
            updateUserProfile( (AccountOpen)log);
        }else if(log instanceof Withdraw){
            updateUserProfile( (Withdraw) log);
        }else if(log instanceof Deposit){
            updateUserProfile( (Deposit) log);
        }else if(log instanceof Transfer){
            updateUserProfile( (Transfer) log);
        }
    }

    public void saveUserProfile( Signup signup){
        UserProfile userProfile = new UserProfile( signup.getUserid(), signup.getUsername(), signup.getBirthday(), signup.getSignupTime());
        userProfileRepository.save(userProfile);
    }

    public void updateUserProfile( AccountOpen accountOpen){
        Optional<UserProfile> userProfile = userProfileRepository.findbyUserId(accountOpen.getUserid());
        if(userProfile.isPresent()){
            UserProfile profile = userProfile.get();
            profile.setOpenAccountNumber( accountOpen.getAccountNumber());
            profile.setOpenAccountTime( accountOpen.getOpenAccountTime());
            profile.setAmount( BigDecimal.ZERO);
            userProfileRepository.save(profile);
        }
    }

    public void updateUserProfile( Deposit deposit){
        Optional<UserProfile> userProfile = userProfileRepository.findbyUserId(deposit.getUserid());
        if(userProfile.isPresent()){
            UserProfile profile = userProfile.get();
            BigDecimal updateAmount = profile.getAmount().add(deposit.getAmount());
            profile.setAmount( updateAmount);
            userProfileRepository.save(profile);
        }
    }

    public void updateUserProfile( Transfer transfer){
        Optional<UserProfile> senderProfile = userProfileRepository.findbyUserId(transfer.getSenderId());
        Optional<UserProfile> receiverProfile = userProfileRepository.findbyUserAccountNumber(transfer.getReceiptAccountNumber());
        if(receiverProfile.isPresent() && senderProfile.isPresent()){
            UserProfile receiver = receiverProfile.get();
            UserProfile sender = senderProfile.get();
            BigDecimal sendAmount = transfer.getSendAmount();

            BigDecimal updateReceiverAmount = receiver.getAmount().add(sendAmount);
            receiver.setAmount( updateReceiverAmount);
            userProfileRepository.save(receiver);

            BigDecimal updateSendAmount = sender.getAmount().subtract(sendAmount);
            sender.setAmount( updateSendAmount);
            userProfileRepository.save(sender);
        }
    }

    public void updateUserProfile( Withdraw withdraw){
        Optional<UserProfile> userProfile = userProfileRepository.findbyUserId( withdraw.getUserid());
        if(userProfile.isPresent()){
            UserProfile profile = userProfile.get();
            BigDecimal updateAmount = profile.getAmount().add(withdraw.getAmount());
            profile.setAmount( updateAmount);
            userProfileRepository.save( profile);
        }
    }


}

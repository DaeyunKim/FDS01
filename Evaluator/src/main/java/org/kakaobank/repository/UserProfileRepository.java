package org.kakaobank.repository;

import org.kakaobank.profile.UserProfile;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserProfileRepository implements ProfileRepository{
    private static Map<Long, UserProfile> userRepository = new ConcurrentHashMap<>();

    public void save(UserProfile userProfile) {
        userRepository.put(userProfile.getUserid(), userProfile);
    }

    @Override
    public Optional<UserProfile> findbyUserAccountNumber(String receiptAccountNumber) {
        Optional<Map.Entry<Long, UserProfile>> first = userRepository.entrySet().stream().filter(element ->
                element.getValue().getOpenAccountNumber().equals(receiptAccountNumber)
        ).findFirst();
        if(first.isPresent()){
            return Optional.of(first.get().getValue());
        }else{
            return Optional.empty();
        }
//        System.out.println(first.get().getValue());

    }

    public Optional<UserProfile> findbyUserId(Long userId) {

        if(userRepository.containsKey(userId)){
            return Optional.of(userRepository.get(userId));
        }else{
            return Optional.empty();
        }



    }
}

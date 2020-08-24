package org.kakaobank.repository;

import org.kakaobank.profile.UserProfile;

import java.util.Optional;

public interface ProfileRepository {
    Optional<UserProfile> findbyUserId(Long userid);
    void save(UserProfile userProfile);
    Optional<UserProfile> findbyUserAccountNumber(String receiptAccountNumber);
}

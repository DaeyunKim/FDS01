package org.kakaobank.repository;

import org.kakaobank.profile.OlderProfile;

import java.util.Optional;

public interface OlderRepository {
    Optional<OlderProfile> findByUserID(Long userid);
}

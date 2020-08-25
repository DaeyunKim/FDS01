package org.kakaobank.repository;

import org.kakaobank.profile.OlderProfile;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TempRepository implements OlderRepository{
    private static Map<Long, OlderProfile> olderRepository= new ConcurrentHashMap<>();

    public void save(Long userid, OlderProfile olderProfile) {
        olderRepository.put(userid,olderProfile);
    }

    @Override
    public Optional<OlderProfile> findByUserID(Long userid) {
        if(olderRepository.containsKey(userid)){
            return Optional.of(olderRepository.get(userid));
        }else{
            return Optional.empty();
        }


    }
}

package org.kakaobank.repository;

import org.kakaobank.profile.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Repository {
    private static Map<Long, User> userRepository = new ConcurrentHashMap<>();
}

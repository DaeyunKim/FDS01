package org.kakaobank.repository;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private Map<Long, User> signUserRepository = new ConcurrentHashMap<>();

    public UserRepository(){

    }

    public Long getUserCount(){
        Long aLong = Long.valueOf(signUserRepository.size());
        return aLong;
    }
    public User saveUser(User user){
        Long id = Long.valueOf(signUserRepository.size()+1);
        this.signUserRepository.put(id,user);
        return user;
    }

    public User getUserByUserID(Long userId){
        User user = null;
        if( signUserRepository.containsKey(userId)){
            user = signUserRepository.get(userId);
        }else{
            System.out.println("찾으려는 아이디가 없습니다.");
        }
        return user;
    }

}

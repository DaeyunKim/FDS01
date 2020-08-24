package org.kakaobank.transaction.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * 가입로그 : Signup
 * 고객번호 : userid
 * 고객명 : username
 * 생년월일 : birthday
 * 가입시각 : signupTime
 */
public class Signup {

    Long userid;
    String username;
    LocalDate birthday;
    LocalDateTime signupTime;

    public Signup(Long uniqueuserId, String username, LocalDate birthday, LocalDateTime signupTime) {
        this.userid = uniqueuserId;
        this.username = username;
        this.birthday = birthday;
        this.signupTime = signupTime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(LocalDateTime signupTime) {
        this.signupTime = signupTime;
    }
}
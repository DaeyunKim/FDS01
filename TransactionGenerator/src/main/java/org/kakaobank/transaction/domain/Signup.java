package org.kakaobank.transaction.domain;

import java.sql.Timestamp;

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
    Timestamp birthday;
    Timestamp signupTime;

    public Signup(Long uniqueuserId, String username, Timestamp birthday, Timestamp signupTime) {
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

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Timestamp getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Timestamp signupTime) {
        this.signupTime = signupTime;
    }
}

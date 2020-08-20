package org.kakaobank.domain;

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
    LocalDateTime birthday;
    LocalDateTime signupTime;
}

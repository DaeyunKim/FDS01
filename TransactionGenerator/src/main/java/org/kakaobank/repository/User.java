package org.kakaobank.repository;

import java.sql.Timestamp;

public class User {

    String username;
    Timestamp birthday;

    public User(String username, Timestamp birthDay) {
        this.username=username;
        this.birthday = birthDay;
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
}

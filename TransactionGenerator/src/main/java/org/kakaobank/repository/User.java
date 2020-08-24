package org.kakaobank.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {

    String username;
    LocalDate birthday;

    public User(String username, LocalDate birthDay) {
        this.username=username;
        this.birthday = birthDay;
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
}

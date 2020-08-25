package org.kakaobank.profile;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserProfile {

    private Long userid;
    private String username;
    private Timestamp birthday;
    private Timestamp registerTime;
    private String openAccountNumber;
    private Timestamp openAccountTime;
    private BigDecimal amount = BigDecimal.ZERO;

    public UserProfile(Long userid, String username, Timestamp birthday, Timestamp registerTime) {
        this.userid = userid;
        this.username = username;
        this.birthday = birthday;
        this.registerTime = registerTime;
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

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getOpenAccountTime() {
        return openAccountTime;
    }

    public void setOpenAccountTime(Timestamp openAccountTime) {
        this.openAccountTime = openAccountTime;
    }

    public String getOpenAccountNumber() {
        return openAccountNumber;
    }

    public void setOpenAccountNumber(String openAccountNumber) {
        this.openAccountNumber = openAccountNumber;
    }
    @Override
    public String toString() {
        return "UserProfile{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", registerTime=" + registerTime +
                ", openAccountNumber='" + openAccountNumber + '\'' +
                ", openAccountTime=" + openAccountTime +
                ", amount=" + amount +
                '}';
    }
}

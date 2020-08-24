package org.kakaobank.profile;

import java.sql.Timestamp;

public class OlderProfile {
    long userid;
    Timestamp registerTime;
    Timestamp openAccountTime;
    Timestamp overAmountTime;

    public OlderProfile(long userid, Timestamp registerTime, Timestamp openAccountTime) {
        this.userid = userid;
        this.registerTime = registerTime;
        this.openAccountTime = openAccountTime;
    }

    public OlderProfile(long userid, Timestamp registerTime, Timestamp openAccountTime, Timestamp overAmountTime) {
        this.userid = userid;
        this.registerTime = registerTime;
        this.openAccountTime = openAccountTime;
        this.overAmountTime = overAmountTime;
    }

    public Timestamp getOverAmountTime() {
        return overAmountTime;
    }

    public void setOverAmountTime(Timestamp overAmountTime) {
        this.overAmountTime = overAmountTime;
    }



    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Timestamp getOpenAccountTime() {
        return openAccountTime;
    }

    public void setOpenAccountTime(Timestamp openAccountTime) {
        this.openAccountTime = openAccountTime;
    }
}

package org.kakaobank.evalutation.domain;

public enum LogType {

    SIGNUP("SIGNUP"), ACCOUNTOPEN("ACCOUNTOPEN"), DEPOSIT("DEPOSIT"), WITHDRAW("WITHDRAW"), TRANSFER("TRANSFER");
    String logType ;
     LogType(String logType){
        this.logType = logType;
    }
}

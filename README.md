#빅데이터 분석 데이터엔지니어

실시간으로 다양한 규칙들의 위반 여부를 판단해 이상 거래를 탐지하는 FDS 이상거래 탐지)를 개발

###로그 유형
한글 : 영문
* 가입로그 : Signup
    * 고객번호 : userid
    * 고객명 : username
    * 생년월일 : birthday
    * 가입시각 : signupTime
* 계좌개설로그 : Accountopen
    * 고객번호 : userid
    * 계좌번호 : accountNumber
    * 거래시각 : transactionTime
* 입금 로그 : Deposit
    * 고객번호 : userid
    * 입금 계좌번호 : accountNumber
    * 입금 금액 : amount
    * 거래시각 : trasactionTime
* 출금로그 : Withdraw
    * 고객번호 : userid
    * 출금계좌번호 : accountNumber
    * 출금 금액 : amount
    * 거래시각 : trasactionTime
* 이체로그 : Transfer
    * 고객번호 : userid 
    * 송금계좌번호 : remittanceAccountNumber
    * 수취은행 : receiptBankName
    * 수취 계좌번호 : receiptAccountNumber
    * 수취계좌주 : receiptUserName
    * 이체금액 : amount
    * 거래시각 : transcationTime



---
###사용 라이브러리 

maven dependency

 JacksonLibrary :  2.11.3

 * jackson-core
 * jackson-annotation
 * jackson-databind

Kafka version : 2.11-2.3.0

```xml
<dependency>
  <groupId>org.apache.kafka</groupId>
  <artifactId>kafka-clients</artifactId>
  <version>2.3.0</version>
</dependency>
```

### 구현 A 방법

1. 회원 가입의 로그에서 60세 이상인 사람들을 Map<Userid, OlderProfile > tempRepository로 별도로 저장해 놓음

   ```java
   OlderProfile (
   long userid; // userId
   Timestamp registerTime; // 등록 시간
   Timestamp openAccountTime; // 계좌등록 시간
   Timestamp overAmountTime; // 특정 금액이상인 경우 시간 등록 
   )
   ```

3. Deposit 또는 Transfer transaction이 일어났을 경우 특정 금액이 추가 됬을 경우 OlderProfile 의 overAmountTime 값 추가
4. Withdraw 또는 Transfre transaction이 일어났을 경우 잔액이 특정금액 이하일 경우 detection을 함 



### 테스트

테스트 조건  

`FDSdetection.java`

```java
private final int detectionAgeLine = 60; //나이
private final int transactionWithInTime = 1; // 특정 금액 이후 10000원 이하가 되기까지의 시간 (테스트에서는 minute로 사용)
private final int registerWithTnTime = 48; // 계좌등록 이내 시간
private final BigDecimal limitExchangeMoney = BigDecimal.valueOf(200000);
private final BigDecimal limitDetectionAmount = BigDecimal.valueOf(500000); // 특정 금액을 오십만원으로 지정
```

```tex
Find Detection
Withdraw{userid=1, accountNumber='814-754-92340', amount=45027, transactionTime=2020-08-25 22:53:27.566}
UserProfile{userid=1, username='morris', birthday=1994-02-28 00:00:00.0, registerTime=2020-08-25 22:53:26.34, openAccountNumber='814-754-92340', openAccountTime=2020-08-25 22:53:26.551, amount=208140}
OlderProfile{userid=1, registerTime=2020-08-25 22:51:51.627, openAccountTime=2020-08-25 22:51:51.957, overAmountTime=2020-08-25 22:51:52.97}
Consumer Detection Error

```



1. `Withdraw{userid=1, accountNumber='814-754-92340', amount=45027, transactionTime=2020-08-25 22:53:27.566}`

2. `UserProfile{userid=1, username='morris', birthday=1994-02-28 00:00:00.0, registerTime=2020-08-25 22:53:26.34, openAccountNumber='814-754-92340', openAccountTime=2020-08-25 22:53:26.551, amount=208140}`

3. `OlderProfile{userid=1, registerTime=2020-08-25 22:51:51.627, openAccountTime=2020-08-25 22:51:51.957, overAmountTime=2020-08-25 22:51:52.97}`

2번의 로그에서 9번의 유저는 208,140만원을 가지고 있다. 이후 1번의 withdraw 로그를 통해서 45,027 금액이 출금됬다고 했을때, 잔액이 20만원 이하이기 때문에 검출됨 






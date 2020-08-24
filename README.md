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

### 규칙 구현

규칙A :  고객의 만 나이가 60세 이상인 경우

- 48시간 이내 신규 개설 된 계좌에 누적 100만원 이상 입금 후, 2시간 이내
          이체 또는 출금으로 잔액이 1만원 이하가 된 경우 
          (단, 이체 또는 출금은 1건 이상일 수 있음)
          

###문제 요구사항



1) Maven 멀티모듈 기반으로 프로젝트 구성  

 

2) 금융 거래 로그를 생성하는 TransactionGenerator를 구현

     (1)  고객 행동을 시뮬레이션 하고 금융 거래 로그를 발생시킨다.

a.  고객의 가장 처음 로그는 “가입 → 계좌개설” 순서로 진행된다.

b.  계좌개설 이후부터는 “입금, 출금, 이체” 중 하나의 거래가 진행된다.

c.  고객의 계좌 잔액은 0원 미만이 될 수 없다.

            (2)  “규칙-A”를 테스트할 수 있는 금융 거래 로그를 발생시켜야 한다.
    
            (3)  금융 거래 로그는 Kafka producer를 사용해 “fds.transactions” 토픽으로 전송한다. 

 


3) 금융 거래 로그를 프로파일하고 이상 거래를 탐지하는 Evaluator를 구현

     (1)  Kafka consumer를 사용해 “fds.transactions” 토픽을 consume 하고 이상 거래 탐지에 필요한 데이터 
          전처리를 수행한다. 

a.  고객별 프로파일 데이터를 저장할 수 있는 Repository 인터페이스를 정의한다.

b.  Repository 인터페이스는 In-Memory, NoSQL, RDBMS 등 다양한 스토리지를 사용해 구현할 수 있다.

            (2) 동기화코드는 지양하고 병렬 처리 등 성능 최적화를 위한 고려가 있어야 한다.  
    
            (3)  “규칙-A”에서 정의한 이상 거래를 100ms 이내에 탐지할 수 있다.
    
            (4)  “규칙-A”와 유사한 탐지 규칙을 수용할 수 있어야 한다.
    
            (5)  “규칙-A”에 의해 탐지된 이상거래 건을 Kafka producer를 사용해 “fds.detections” 토픽으로 전송한다. 

 

4) 프로그램 종료 시 사용된 리소스를 올바르게 정리

 

5) 설계 시 주요 고려사항을 README.md 파일에 서술

    (1)  “규칙-A” 동작을 테스트할 수 있는 금융 거래 로그와 테스트 결과 첨부 필수





3. 제약사항

1) 언어는 Java, JDK8 이상 사용

2) 스토리지(IMDG, NoSQL, RDBMS 등), Kafka client, Junit, Json, Logging 라이브러리 사용 가능 

3) 그 외 서드 파티 프레임워크/라이브러리 사용 금지 (예: Spring, Guava, Lombok, Apache Commons 등)

 

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


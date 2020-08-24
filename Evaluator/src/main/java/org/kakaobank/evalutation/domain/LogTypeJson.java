package org.kakaobank.evalutation.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Deposit.class, name = "deposit"),
        @JsonSubTypes.Type(value = Signup.class, name = "signup"),
        @JsonSubTypes.Type(value = Transfer.class, name = "transfer"),
        @JsonSubTypes.Type(value = Withdraw.class, name = "withdraw"),
        @JsonSubTypes.Type(value = AccountOpen.class, name = "accountopen")
})
public class LogTypeJson {
}

//package com.smu.investimentos.auth.account.api.accounts;
//
//import com.smu.investimentos.auth.account.domain.model.Account;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.OffsetDateTime;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class AccountDetailedResponse {
//        private Long id;
//        private InvestorResponse investor;
//        private String numberAccount;
//        private String agency;
//        private double balance;
//        private Account.StatusAccount statusAccount;
//        private OffsetDateTime createdAt;
//
//    public static AccountDetailedResponse of(Account account, InvestorResponse investor) {
//        return new AccountDetailedResponse(
//                account.getId(),
//                investor,
//                account.getNumberAccount(),
//                account.getAgency(),
//                account.getBalance(),
//                account.getStatusAccount(),
//                account.getCreatedAt());
//    }
//
//}

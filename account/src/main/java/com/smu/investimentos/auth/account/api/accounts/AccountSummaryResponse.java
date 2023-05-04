//package com.smu.investimentos.auth.account.api.accounts;
//
//import com.smu.investimentos.auth.account.domain.model.Account;
//
//public class AccountSummaryResponse {
//    private Long id;
//    private String title;
//
//    public AccountSummaryResponse(Long id, String title) {
//        this.id = id;
//        this.title = title;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public static AccountSummaryResponse of(Account account) {
//        return new AccountSummaryResponse(account.getId(), account.getTitle());
//    }
//
//}

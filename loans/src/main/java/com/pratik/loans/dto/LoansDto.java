package com.pratik.loans.dto;

import lombok.Data;

@Data
public class LoansDto {
    private long loanId;
    private String loanNumber;
    private String loanType;
    private String MobileNumber;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;


}

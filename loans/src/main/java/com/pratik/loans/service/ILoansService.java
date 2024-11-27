package com.pratik.loans.service;

import com.pratik.loans.dto.LoansDto;

public interface ILoansService {
    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoan(String mobileNumber);
    LoansDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDto loansDto);
    boolean deleteLoan(String mobileNumber);
}

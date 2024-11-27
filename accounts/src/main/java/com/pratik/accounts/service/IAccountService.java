package com.pratik.accounts.service;

import com.pratik.accounts.dto.CustomerDto;


public interface IAccountService {
    /**
     *
     * @param accountDto The data transfer object containing customer information required for account creation.

     */
    void createAccount(CustomerDto accountDto);
    /*
     * @return account details based on the mobileNumber
     */
    CustomerDto fetchAccount (String mobileNumber);
    /*
    @update
     */
    boolean updateAccount (CustomerDto accountDto);

    boolean deleteAccount (String mobileNumber);

}

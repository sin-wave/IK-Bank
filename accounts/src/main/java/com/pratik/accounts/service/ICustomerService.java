package com.pratik.accounts.service;

import com.pratik.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    /**
     *
     * @param mobileNumber
     * @return
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}

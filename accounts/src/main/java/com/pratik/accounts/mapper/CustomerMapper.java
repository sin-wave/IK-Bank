package com.pratik.accounts.mapper;

import com.pratik.accounts.dto.CustomerDetailsDto;
import com.pratik.accounts.dto.CustomerDto;
import com.pratik.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDtoDto) {
        customerDetailsDtoDto.setName(customer.getName());
        customerDetailsDtoDto.setEmail(customer.getEmail());
        customerDetailsDtoDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDtoDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}

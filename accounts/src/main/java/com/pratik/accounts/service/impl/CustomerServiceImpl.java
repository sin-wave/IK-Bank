package com.pratik.accounts.service.impl;

import com.pratik.accounts.dto.AccountsDto;
import com.pratik.accounts.dto.CardsDto;
import com.pratik.accounts.dto.CustomerDetailsDto;
import com.pratik.accounts.dto.LoansDto;
import com.pratik.accounts.entity.Accounts;
import com.pratik.accounts.entity.Customer;
import com.pratik.accounts.exception.ResourceNotFoundException;
import com.pratik.accounts.mapper.AccountsMapper;
import com.pratik.accounts.mapper.CustomerMapper;
import com.pratik.accounts.repository.AccountsRepository;
import com.pratik.accounts.repository.CustomerRepository;
import com.pratik.accounts.service.ICustomerService;
import com.pratik.accounts.service.client.CardsFeignClient;
import com.pratik.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private  AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId()))
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
//        behind this line my feignClient will connect with the eureka server and try to get the loans instance details
//        it will perform some load balancing and invoke the actual microservice

        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}

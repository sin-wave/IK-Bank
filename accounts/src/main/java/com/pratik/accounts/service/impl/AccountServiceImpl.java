package com.pratik.accounts.service.impl;

import com.pratik.accounts.constants.AccountConstants;
import com.pratik.accounts.dto.AccountsDto;
import com.pratik.accounts.dto.CustomerDto;
import com.pratik.accounts.entity.Accounts;
import com.pratik.accounts.entity.Customer;
import com.pratik.accounts.exception.CustomerAlreadyExistsException;
import com.pratik.accounts.exception.ResourceNotFoundException;
import com.pratik.accounts.mapper.AccountsMapper;
import com.pratik.accounts.mapper.CustomerMapper;
import com.pratik.accounts.repository.AccountsRepository;
import com.pratik.accounts.repository.CustomerRepository;
import com.pratik.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer  = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer>  optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){throw new CustomerAlreadyExistsException("Customer already present with given mobile number"+customerDto.getMobileNumber());
        }
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setUpdatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
//        customer.setUpdatedBy("Anonymous");
        Customer saveCustomer  = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(saveCustomer));
    }



    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Anonymous");
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;

    }
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", String.valueOf(customer.getCustomerId())));
        CustomerDto customerDto =CustomerMapper.mapToCustomerDto(customer,new CustomerDto() );
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto!=null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(()->new ResourceNotFoundException("Accounts", "accountNumber", String.valueOf(accountsDto.getAccountNumber())));
            AccountsMapper.mapToAccounts(accountsDto,accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer","customerId", String.valueOf(customerId)));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;


        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}

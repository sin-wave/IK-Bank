package com.pratik.accounts.service.client;

import com.pratik.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")

//we need to mention a value which is cards because the same value, which is cards we have used to register with the eureka server
//so that my feign client connect with the eureka server at the run time and it will try to get all the instance details with the logical name cards.

public interface LoansFeignClient {
/* Inside this interface, I am going to create a new abstract method and whatever abstract method we are
    going to create inside this interface, this method signature has to be matched with the actual RESTAPI method
    that I have defined the card microservice.
    we have to import cardsDto and loanDto in account DTO
*/
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);

}

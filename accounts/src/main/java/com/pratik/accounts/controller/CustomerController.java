package com.pratik.accounts.controller;

import com.pratik.accounts.dto.CustomerDetailsDto;
import com.pratik.accounts.dto.ErrorResponseDto;
import com.pratik.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = " REST APIs for Customer in AKP Bank",
        description = " REST APIs in AKP Bank to fetch Customer Details"

)
@RestController
@RequestMapping(path = "api/",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService icustomerService;

//    public CustomerController(ICustomerService icustomerService) {
//        this.icustomerService = icustomerService;
//    }

    @Operation(
            summary = "Fetch Account REST API ",
            description = "REST API for fetch  Customer & Accounts in AKP Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internl Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    }


    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be of 10 digits")
                                                                   String mobileNumber) {
        CustomerDetailsDto customerDetailsDto = icustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }



}

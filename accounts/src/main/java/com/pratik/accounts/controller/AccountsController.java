package com.pratik.accounts.controller;

import com.pratik.accounts.constants.AccountConstants;
import com.pratik.accounts.dto.AccountsContactInfoDto;
import com.pratik.accounts.dto.CustomerDto;
import com.pratik.accounts.dto.ErrorResponseDto;
import com.pratik.accounts.dto.ResponseDto;
import com.pratik.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Component
@RestController
@RequestMapping(path = "api/",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "Crud REST APIs for Accounts in AKP Bank",
        description = "CRUD REST APIs in AKP Bank for CREATE, UPDATE, FETCH AND DELETE account Details"

)
public class AccountsController {

    private IAccountService iAccountService;

//    @Value("${build.version}")
//    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;


    @Operation(
            summary = "Create Account REST API ",
            description = "REST API for create new Customer & Accounts in AKP Bank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"

    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Account REST API ",
            description = "REST API for fetch  Customer & Accounts in AKP Bank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"

    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountsDetail(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                                                               String mobileNumber ){
        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


    @Operation(
            summary = "Create Account REST API ",
            description = "REST API for create new Customer & Accounts in AKP Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto>updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417 ,AccountConstants.MESSAGE_417_UPDATE));
        }
    }



    @Operation(
            summary = "DELETE Account REST API ",
            description = "REST API for Delete Customer & Accounts in AKP Bank")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                                                               String mobileNumber){
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_DELETE));
        }
    }
//    @Operation(
//            summary = "Get Build information",
//            description = "Get Build information that is deployed into accounts microservice"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "HTTP Status OK"
//            ),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "HTTP Status Internal Server Error",
//                    content = @Content(
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    }
//    )
//    @GetMapping("/build-info")
//    public ResponseEntity<String> getBuildInfo() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(buildVersion);
//    }

    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }

}

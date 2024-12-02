package com.pratik.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Account,Cards,Loans and Customer level information"
)
public class CustomerDetailsDto {
    @Schema(
            name = "Name of the Customer",example = "AKP Bank"
    )
    @NotEmpty(message = "Name can't be empty or null")
    @Size(min = 5 ,max =50 ,message = "The length of the customer is in between 5 and 50")
    private String name;


    @Schema(
            example = "av.pratik@gmail.com"
    )
    @NotEmpty(message = "Email can't be empty or null")
    @Email(message = "Email address should be valid value")
    private String email;
    @Schema(
            example = "8972101010"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String mobileNumber;
    //we are sending aggregated information of account within customer dta we have two options to declare one more class
    // or inside this we create on one filed of type AccountsDto
    @Schema(
            example = "Account details of the customer"
    )
    private AccountsDto accountsDto;
    @Schema(
            example = "Loans details of the customer"
    )
    private LoansDto loansDto;
    @Schema(
            example = "Cards details of the customer"
    )
    private CardsDto cardsDto;
}

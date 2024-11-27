package com.pratik.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Customer to hold Account  level information"
)
public class AccountsDto {
    @Schema(
            description = "Account Number of AKP Bank"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number must be 10 digits")
    private long accountNumber;

    @Schema(
            description = "Account Type of AKP Bank",
            example = "Saving Account"
    )
    @NotEmpty(message = "Account Type can't  be empty or null")
    private String accountType;
    @Schema(
            description = "BranchAddress of AKP Bank",
            example = "Puttur"
    )
    @NotEmpty(message = "BranchAddress can't be null or empty")
    private String branchAddress;
}

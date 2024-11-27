package com.pratik.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDto {
@Schema(
        description = "Status Message in the Response",
        example = "Request Processed Successfully"
)
    private String StatusCode;
    private String statusMessage;
}

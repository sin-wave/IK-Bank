package com.pratik.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "ErrorResponse",
        description ="Schema to hold Error Response"
)
public class ErrorResponseDto {

    @Schema(
            description = "API Invoked by the Client"
    )
    private String apiPath;
    @Schema(
            description = "Error code representing the Error Happened"
    )

    private HttpStatus errorCode;
    @Schema(
            description = "Error Messages representing the Error Happened"
    )
    private String errorMessage;
    @Schema(
            description = "Error Time representing the Error Happened"
    )
    private LocalDateTime errorTime;
}

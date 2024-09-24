package com.centrix.fund_transfer_email.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String toEmail;

    @NotBlank(message = "Username is required.")
    private String userName;

    @NotBlank(message = "Recipient name is required.")
    private String recipientName;

    @NotNull(message = "Amount is required.")
    @Positive(message = "Amount must be positive.")
    private Double amount;

    @NotBlank(message = "Transaction ID is required.")
    private String transactionId;
}

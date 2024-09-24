package com.centrix.fund_transfer_email.controllers;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.centrix.fund_transfer_email.services.EmailService;
import com.centrix.fund_transfer_email.models.requests.EmailRequest;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public String sendEmail( @Valid @RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendFundTransferEmail(
                    emailRequest.getToEmail(),
                    emailRequest.getUserName(),
                    emailRequest.getRecipientName(),
                    emailRequest.getAmount(),
                    emailRequest.getTransactionId()
            );
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}

package com.centrix.fund_transfer_email.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    //Sends a fund transfer confirmation email to the user.
    public void sendFundTransferEmail(String toEmail, String userName, String recipientName, double amount, String transactionId) throws MessagingException {
        log.info("send email service start->");
        String formattedAmount = formatAmount(amount);
        log.info("formatted amount: {}", formattedAmount);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Create email content using Thymeleaf template
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("recipientName", recipientName);
        context.setVariable("amount", formattedAmount);
        context.setVariable("transactionId", transactionId);

        // Generate the HTML content from the Thymeleaf template ('emailTemplate.html')
        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setTo(toEmail);
        helper.setSubject("Fund Transfer Confirmation");
        helper.setText(htmlContent, true);  // true for HTML content

        mailSender.send(message);
        log.info("Send email service -> end");
    }
    //Formats a double amount into a string with commas and two decimal places.
    private String formatAmount(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(amount);
    }

}


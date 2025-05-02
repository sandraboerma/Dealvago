package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.dto.OrderlineDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmationEmail(String recipientEmail, List<OrderlineDto> orderItems) {
        StringBuilder orderDetails = new StringBuilder();
        int total = 0;

        for (OrderlineDto item : orderItems) {
            orderDetails.append("Product: ")
                    .append(item.getProductDto().getName())
                    .append("\n")
                    .append("Quantity: ")
                    .append(item.getQuantity())
                    .append(", Price: ")
                    .append(item.getTotalPrice())
                    .append("\n");
            total += item.getTotalPrice();
        }

        orderDetails.append("\nTotal: ").append(total).append(" kr");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Order Confirmation");
        message.setText("Thank you for your order!\n\nOrder Details:\n" + orderDetails);

        mailSender.send(message);
        logger.info("Order confirmation email sent to {}", recipientEmail);
    }

}
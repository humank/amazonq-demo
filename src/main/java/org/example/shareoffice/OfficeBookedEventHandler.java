package org.example.shareoffice;

import org.example.shareoffice.services.EmailService;
import org.example.shareoffice.services.InvoiceService;

public class OfficeBookedEventHandler {
    private InvoiceService invoiceService;
    private EmailService emailService;

    public OfficeBookedEventHandler(InvoiceService invoiceService, EmailService emailService) {
        this.invoiceService = invoiceService;
        this.emailService = emailService;
    }

    public void handleOfficeBookedEvent(OfficeBooked event) {
        // Generate an invoice
        invoiceService.generateInvoice(event);

        // Send a confirmation email
        emailService.sendConfirmationEmail(event);
    }
}

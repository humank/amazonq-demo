package org.example.shareoffice.services;

import org.example.shareoffice.OfficeBooked;

public interface EmailService {
    void sendConfirmationEmail(OfficeBooked event);
}

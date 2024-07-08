package org.example.shareoffice.services;

import org.example.shareoffice.OfficeBooked;

public interface InvoiceService {
    void generateInvoice(OfficeBooked event);
}

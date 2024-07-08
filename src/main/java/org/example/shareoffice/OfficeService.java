package org.example.shareoffice;

import org.example.shareoffice.services.EmailService;
import org.example.shareoffice.services.InvoiceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfficeService {
    private List<Office> officeList;
    private InvoiceService invoiceService;
    private EmailService emailService;

    public OfficeService(List<Office> officeList) {
        this.officeList = officeList;
    }

    public List<Office> getAvailableOffices(LocalDate startDate, LocalDate endDate) {
        ArrayList returnList = new ArrayList<Office>();
        for (Office office: officeList) {
            if (office.getStartDate().equals(startDate) || office.getStartDate().isAfter(startDate)){
                if(office.getEndDate().equals(endDate) || office.getEndDate().isBefore(endDate)){
                    returnList.add(office);
                }
            }
        }
        return returnList;
    }

    public boolean bookOffice(Office office, LocalDate startDate, LocalDate endDate) {
        if (isOfficeAvailable(office, startDate, endDate)) {
            OfficeBooked officeBookedEvent = new OfficeBooked(office, startDate, endDate);
            OfficeBookedEventHandler eventHandler = new OfficeBookedEventHandler(invoiceService, emailService);
            eventHandler.handleOfficeBookedEvent(officeBookedEvent);
            return true;
        }
        return false;
    }

    private boolean isOfficeAvailable(Office office, LocalDate startDate, LocalDate endDate) {
        List<Office> availableOffices = getAvailableOffices(startDate, endDate);
        return availableOffices.contains(office);
    }
}

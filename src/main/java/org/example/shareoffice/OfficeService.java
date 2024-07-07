package org.example.shareoffice;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfficeService {
    private List<Office> officeList;

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
}

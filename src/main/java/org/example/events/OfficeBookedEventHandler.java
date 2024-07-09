package org.example.events;

import java.util.ArrayList;
import java.util.List;

public class OfficeBookedEventHandler implements EventHandler {
    private List<OfficeBookedEvent> events = new ArrayList<>();

    @Override
    public void handleEvent(Object event) {
        if (event instanceof OfficeBookedEvent) {
            events.add((OfficeBookedEvent) event);
        }
    }

    @Override
    public boolean isEventTriggered(String eventName) {
        return events.size()>0;
        //return events.stream().anyMatch(event -> event.getClass().getSimpleName().equals(eventName));
    }

    @Override
    public Object getLastEvent(String eventName) {
        return events.stream()
                .filter(event -> event.getClass().getSimpleName().equals(eventName))
                .reduce((first, second) -> second)
                .orElse(null);
    }
}

package org.example.events;

public interface EventHandler {
    void handleEvent(Object event);
    boolean isEventTriggered(String eventName);
    Object getLastEvent(String eventName);
}

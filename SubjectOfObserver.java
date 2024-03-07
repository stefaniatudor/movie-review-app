package org.example;

import java.util.List;

public interface SubjectOfObserver {
    void notifyObservers(String message);

void addObserver(NotificationObserver observer);
void deleteObserver(NotificationObserver observer);
}

package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Regular extends User implements RequestsManager{
    public Regular(String fullName) {
        super(fullName, AccountType.REGULAR);
    }

    @Override
    public void createRequest(Request request) {
        RequestsHolder.addRequest(request);
    }

    @Override
    public void removeRequest(Request request) {
        RequestsHolder.removeRequest(request);
    }

    @Override
    public void notify(String message) {
        this.addNotification(message);
    }
}

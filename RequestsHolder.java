package org.example;

import org.example.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestsHolder {
    private static List<Request> requests = new ArrayList<>();

    public static void addRequest(Request request) {
        requests.add(request);
    }

    public static void removeRequest(Request request) {
        requests.remove(request);
    }

    public static List<Request> getRequests() {
        return requests;
    }

}

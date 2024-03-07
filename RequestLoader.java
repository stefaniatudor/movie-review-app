package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

public class RequestLoader {

    private static Request loadRequest(JsonNode requestNode) {
        Request.RequestType type = null;
        LocalDateTime createdDate = null;
        String username = null;
        String name = null;
        String rezolver = null;
        String description = null;

        if (requestNode.has("type")) {
            type = Request.RequestType.valueOf(Utils.curateString(String.valueOf(requestNode.get("type"))));
        }

        if (requestNode.has("createdDate")) {
            createdDate = LocalDateTime.parse(
                    Utils.curateString(String.valueOf(requestNode.get("createdDate"))),
                    Request.getFormatter()
            );
        }

        if (requestNode.has("username")) {
            username = Utils.curateString(String.valueOf(requestNode.get("username")));
        }

        if (requestNode.has("to")) {
            rezolver = Utils.curateString(String.valueOf(requestNode.get("to")));
        }

        if (requestNode.has("description")) {
            description = Utils.curateString(String.valueOf(requestNode.get("description")));
        }

        Request request;
        if(type.equals(Request.RequestType.MOVIE_ISSUE)||type.equals(Request.RequestType.ACTOR_ISSUE)){
            if (requestNode.has("actorName")) {
                name = Utils.curateString(String.valueOf(requestNode.get("actorName")));
            } else if (requestNode.has("movieTitle")) {
                name = Utils.curateString(String.valueOf(requestNode.get("movieTitle")));
            }
            request = new Request(type,createdDate,name,description,username,rezolver);
        }
        else
            request = new Request(type,createdDate,description,username,rezolver);

        User creatorUser = UsersManager.getUserByUsername(username);
        request.addObserver(creatorUser);

        return request;
    }

    public static void loadEntriesFromJSONFile(Utils.FileType fileType) throws IOException {
        String jsonString = Utils.extractTextFromFile(fileType);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode requestNodes = objectMapper.readTree(jsonString);

        for (JsonNode requestNode : requestNodes) {
            Request r = RequestLoader.loadRequest(requestNode);
            RequestsHolder.addRequest(r);
        }
        IMDB.getInstance().requests = RequestsHolder.getRequests();
    }
}

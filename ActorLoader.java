package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ActorLoader{

    private static Actor loadActor(JsonNode actorNode){
        Actor actor = new Actor();

        if(actorNode.has("name"))
            actor.setName(Utils.curateString(String.valueOf(actorNode.get("name"))));

        if(actorNode.has("biography"))
            actor.setBio(Utils.curateString(String.valueOf(actorNode.get("biography"))));

        if(actorNode.has("performances")){
            for(JsonNode performancesNode:actorNode.get("performances")){
                String productionTitle = Utils.curateString(String.valueOf(performancesNode.get("title")));
                Production production = ProductionsManager.getProductionByName(productionTitle);
                if(production != null){
                    actor.addPerformances(production);
                }
                else{
                    if(Utils.curateString(String.valueOf(performancesNode.get("type"))).equals("Movie")) {
                        actor.addPerformances(new Movie(productionTitle));
                    }
                    else {
                        actor.addPerformances(new Series(productionTitle));
                    }
                }
            }
        }

        return actor;
    }

    public static void loadEntriesFromJSONFile(Utils.FileType fileType) throws IOException {
        String jsonString = Utils.extractTextFromFile(fileType);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actorNodes = objectMapper.readTree(jsonString);

        for (JsonNode actorNode : actorNodes) {
            Actor a = ActorLoader.loadActor(actorNode);
            ActorsManager.addActors(a);
        }
    }

}

package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UserLoader {

    public static User loadUser(JsonNode userNode) {
        String username = String.valueOf(Utils.curateString(String.valueOf(userNode.get("username"))));
        AccountType type = AccountType.valueOf(Utils.curateString(String.valueOf((userNode.get("userType"))).toUpperCase()));

        User user = UserFactory.createUser(username, type);

        if(userNode.has("experience") && !type.equals(AccountType.ADMIN)){
            user.updateExperience(Integer.valueOf(Utils.curateString(String.valueOf(userNode.get("experience")))));
        }

        InformationBuilder infoBuilder = new InformationBuilder();
        JsonNode informationNode = userNode.get("information");
        infoBuilder.setCredentials(new Credentials(Utils.curateString(String.valueOf(informationNode.get("credentials").get("email"))),
                                                Utils.curateString(String.valueOf(informationNode.get("credentials").get("password")))));
        if(informationNode.has("name")) {
            infoBuilder.setName(Utils.curateString(String.valueOf(informationNode.get("name"))));
        }
        if(informationNode.has("country")) {
            infoBuilder.setCountry(Utils.curateString(String.valueOf(informationNode.get("country"))));
        }
        if(informationNode.has("age")){
            infoBuilder.setAge(Integer.valueOf(String.valueOf(informationNode.get("age"))));
        }
        if(informationNode.has("gender")){
            infoBuilder.setGender(Utils.curateString(String.valueOf(informationNode.get("gender"))));
        }
        if(informationNode.has("birthDate")){
            infoBuilder.setBirthDate(Utils.curateString(String.valueOf(informationNode.get("birthDate"))));
        }

        user.setUserInfo(infoBuilder.build());
        
        if(userNode.has("favoriteProductions")){
            userNode.get("favoriteProductions").forEach( p -> {
                Production production = ProductionsManager.getProductionByName(Utils.curateString(String.valueOf(p)));
                user.addFavorite(production);
            });
        }

        if(userNode.has("favoriteActors")){
            userNode.get("favoriteActors").forEach( a -> {
                Actor actor = ActorsManager.getActorByName(Utils.curateString(String.valueOf(a)));
                user.addFavorite(actor);
            });
        }

        if(userNode.has("notifications")){
            userNode.get("notifications").forEach( n -> user.addNotification(String.valueOf(n)));
        }

        if (user instanceof Staff)
            return loadStaffFields((Staff) user,userNode);
        else
            return user;
    }

    public static void loadEntriesFromJSONFile(Utils.FileType fileType) throws IOException {
        String jsonString = Utils.extractTextFromFile(fileType);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode userNodes = objectMapper.readTree(jsonString);

        for (JsonNode userNode : userNodes) {
            User u = UserLoader.loadUser(userNode);
            UsersManager.addUser(u);
        }
    }

    public static Staff loadStaffFields(Staff staff,JsonNode staffNode) {
        if (staffNode.has("productionsContribution")) {
            for (JsonNode productionNode : staffNode.get("productionsContribution")) {
                String productionName = Utils.curateString(String.valueOf(productionNode));
                Production production = ProductionsManager.getProductionByName(productionName);
                staff.addProductionContribution(production);
            }
        }

        if (staffNode.has("actorsContribution")) {
            for (JsonNode actorNode : staffNode.get("actorsContribution")) {
                String actorName = Utils.curateString(String.valueOf(actorNode));
                Actor actor = ActorsManager.getActorByName(actorName);
                staff.addActorContribution(actor);

            }
        }

        return staff;
    }
}

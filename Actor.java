package org.example;
import org.graalvm.collections.Pair;

import java.util.ArrayList;
import java.util.List;

public class Actor {
    private String name;
    private String bio;
    private List<Pair<Production,String>> performances = new ArrayList<>();

    public Actor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Pair<Production, String>> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Pair<Production, String>> performances) {
        this.performances = performances;
    }

    public void addPerformances(Production p){
        Pair<Production,String> perecheDeAdaugat = null;
        if(p instanceof Movie)
            perecheDeAdaugat = Pair.create(p,"Movie");
        else if (p instanceof Series)
            perecheDeAdaugat = Pair.create(p,"Series");
        performances.add(perecheDeAdaugat);
    }

    public void displayInfo() {
        // Implement logic to display actor information
        System.out.println("Actor Information:");
        System.out.println("Name: " + getName());
        System.out.println("Bio: " + getBio());

        System.out.println("Performances:");
        for (Pair<Production, String> performance : performances) {
            Production production = performance.getLeft();

            if (production instanceof Movie) {
                System.out.println("  Title: " + production.getTitle());
                System.out.println("  Type: Movie");
            } else if (production instanceof Series) {
                System.out.println("  Title: " + production.getTitle());
                System.out.println("  Type: Series");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Actor Information:\n");
        result.append("Name: ").append(name != null ? name : "N/A").append("\n");
        result.append("Bio: ").append(bio != null ? bio : "N/A").append("\n");

        result.append("\nPerformances:\n");
        for (Pair<Production, String> performance : performances) {
            Production production = performance.getLeft();
            result.append("  Title: ").append(production.getTitle()).append("\n");

            if (production instanceof Movie) {
                result.append("  Type: Movie\n");
            } else if (production instanceof Series) {
                result.append("  Type: Series\n");
            }
        }

        return result.toString();
    }

}

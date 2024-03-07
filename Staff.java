package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Staff extends User implements StaffInterface {
    private List<Request> assignedRequests;
    private List<Production> productionsContribution;
    private List<Actor> actorsContribution;

    public Staff(String fullName, AccountType accountType) {
        super(fullName, accountType);
        productionsContribution = new ArrayList<>();
        actorsContribution = new ArrayList<>();
    }

    public List<Production> getProductionsContribution() {
        return productionsContribution;
    }

    public List<Actor> getActorsContribution() {
        return actorsContribution;
    }

    public void addProductionContribution(Production production){
        productionsContribution.add(production);
    }

    public void addActorContribution(Actor actor){
        actorsContribution.add(actor);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("Productions Contributed:");
        for (Production production : productionsContribution) {
            System.out.println("- " + production.getTitle());
        }

        System.out.println("Actors Contributed:");
        for (Actor actor : actorsContribution) {
            System.out.println("- " + actor.getName());
        }
    }
}


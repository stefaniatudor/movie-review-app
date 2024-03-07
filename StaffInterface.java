package org.example;

import org.example.Actor;
import org.example.Production;

public interface StaffInterface {
    void addProductionSystem(Production production);
    void addActorSystem(Actor actor);
    void removeProductionSystem(String name);
    void removeActorSystem(String name);
    void updateProduction(Production production);
    void updateActor(Actor actor);
    void resolveUserRequests();
}

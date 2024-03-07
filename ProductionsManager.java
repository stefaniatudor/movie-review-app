package org.example;

import java.util.List;

public class ProductionsManager {

    public static List<Production> getProductions() {
        return IMDB.getInstance().getProductions();
    }

    public static void addProduction(Production p){
        IMDB.getInstance().getProductions().add(p);
    }

    public static void removeProduction(Production p){
        IMDB.getInstance().getProductions().remove(p);
    }

    public static Production getProductionByName(String name){
        for(Production p:IMDB.getInstance().getProductions()){
            if(p.getTitle().equals(name))
                return p;
        }
        return null;
    }
}

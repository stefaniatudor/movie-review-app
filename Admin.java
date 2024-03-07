package org.example;

import org.graalvm.collections.Pair;

import java.util.*;

public class Admin extends Staff {
    public Admin(String fullName) {
        super(fullName, AccountType.ADMIN);
    }

    @Override
    public void addProductionSystem(Production production) {
        ProductionsManager.addProduction(production);
        System.out.println("Productia a fost adaugata");
    }

    @Override
    public void addActorSystem(Actor actor) {
        ActorsManager.addActors(actor);
        System.out.println("Actorul a fost adaugat");
    }

    @Override
    public void removeProductionSystem(String name) {
        Production movie = ProductionsManager.getProductionByName(name);
        if (movie != null) {
            ProductionsManager.removeProduction(movie);
            System.out.println("Productia a fost stearsa");
        } else {
            System.out.println("Nu exista aceasta productie");

        }
    }

    @Override
    public void removeActorSystem(String name) {
        Actor actor = ActorsManager.getActorByName(name);
        if (actor != null) {
            ActorsManager.removeActors(actor);
            System.out.println("Actorul a fost sters");
        } else {
            System.out.println("Actorul nu exista");
        }
    }

    @Override
    public void updateProduction(Production production) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modificare productie " + production.getTitle());

        System.out.println("Introduceti numele modificat al productiei(Apasarea tastei ENTER nu modifica valoarea):");
        String newName = scanner.nextLine();
        if (!newName.isEmpty())
            production.setTitle(newName);

        System.out.println("Introduceti noul plot al productiei (Apasarea tastei ENTER nu modifica valoarea):");
        String newPlot = scanner.nextLine();
        if (!newPlot.isEmpty())
            production.setPlot(newPlot);

        System.out.println("Introduceti noul an al lansarii al productiei (Apasarea tastei ENTER nu modifica valoarea):");
        String newYear = scanner.nextLine();
        if (!newYear.isEmpty())
            production.setReleaseYear(Integer.parseInt(newYear));

        String option = "";
        while (!option.equals("F")) {
            System.out.print("Optiuni modifcare genuri ADAUGARE-A STERGERE-R FARAMODIFCARI-F [A/R/F]:");
            option = scanner.nextLine();
            if (option.equals("A")) {
                System.out.println("Introduceti genul:");
                Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());
                production.addGenre(genre);
            } else if (option.equals("R")) {
                System.out.println("Introduceti genul:");
                Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());
                for (Genre g : production.getGenres())
                    if (g.equals(genre))
                        production.getGenres().remove(genre);
            }
        }

        option = "";
        while (!option.equals("F")) {
            System.out.print("Optiuni modifcare directori ADAUGARE-A STERGERE-R FARAMODIFCARI-F [A/R/F]:");
            option = scanner.nextLine();
            if (option.equals("A")) {
                System.out.println("Introduceti nume director:");
                String directorName = scanner.nextLine();
                production.addDirectors(directorName);
            } else if (option.equals("R")) {
                System.out.println("Introduceti nume director:");
                String directorName = scanner.nextLine();
                production.getDirectors().remove(directorName);
            }
        }

        option = "";
        while (!option.equals("F")) {
            System.out.print("Optiuni modifcare actori ADAUGARE-A STERGERE-R FARAMODIFCARI-F [A/R/F]:");
            option = scanner.nextLine();
            if (option.equals("A")) {
                System.out.println("Introduceti nume actori:");
                String actorName = scanner.nextLine();
                production.addActors(actorName);
            } else if (option.equals("R")) {
                System.out.println("Introduceti nume actori:");
                String actorName = scanner.nextLine();
                production.getActors().remove(actorName);
            }
        }

        if (production instanceof Movie) {
            System.out.println("Introduceti durata modificata a productiei(Apasarea tastei ENTER nu modifica valoarea):");
            String newDuration = scanner.nextLine();
            if (!newDuration.isEmpty())
                ((Movie) production).setDuration(Double.parseDouble(newDuration));
        } else if (production instanceof Series) {
            String seasonName = "placehodler";
            while (!seasonName.isEmpty()) {
                System.out.println("Introduceti numele sezonului(Apasarea tastei ENTER nu modifica nici un sezon):");
                seasonName = scanner.nextLine();
                if (((Series) production).getSeasons().get(seasonName) != null) {
                    System.out.print("Optiuni modifcare sezonului MODIFCARE_NUME:M STERGERE_SEZON:R MODIFICARE_EPISOADE-E[M/R/E]:");
                    option = scanner.nextLine();
                    if (option.equals("M")) {
                        System.out.println("Introduceti numele modificat al sezonului:\"");
                        String newSeasonName = scanner.nextLine();
                        LinkedHashMap newSeasons = new LinkedHashMap();
                        for (Map.Entry<String, List<Episode>> entry : ((Series) production).getSeasons().entrySet()) {
                            if (!entry.getKey().equals(seasonName))
                                newSeasons.put(entry.getKey(), entry.getValue());
                            else
                                newSeasons.put(newSeasonName, entry.getValue());
                        }
                        ((Series) production).setSeasons(newSeasons);
                    } else if (option.equals("R")) {
                        ((Series) production).getSeasons().remove(seasonName);
                    } else if (option.equals("E")) {
                        List<Episode> episodeList = ((Series) production).getSeasons().get(seasonName);
                        option = "";
                        while (!option.equals("F")) {
                            System.out.println("Optiuni modifcare episoade ADAUGARE_EPISOD-A STERGERE_EPISOD-R " +
                                    "MODIFCARE_NUME_EPISOD:M MODIFCARE_DURATA_EPISOD-D FARA_MODIFCARE-F:[A/R/M/D/F]:");
                            option = scanner.nextLine();
                            if (option.equals("A")) {
                                System.out.println("Introduceti numele episodului");
                                String newEpisodeName = scanner.nextLine();
                                System.out.println("Introduceti durata episodului");
                                String newDuration = scanner.nextLine();
                                episodeList.add(new Episode(newEpisodeName, Double.parseDouble(newDuration)));
                            } else if (option.equals("R")) {
                                System.out.println("Introduceti numele episodului");
                                String episodeName = scanner.nextLine();
                                for (Episode e : episodeList)
                                    if (e.getTitle().equals(episodeName))
                                        episodeList.remove(e);
                            } else if (option.equals("M")) {
                                System.out.println("Introduceti numele episodului:");
                                String episodeName = scanner.nextLine();
                                System.out.println("Introduceti numele nou al episodului:");
                                String newEpisodeName = scanner.nextLine();
                                for (Episode e : episodeList)
                                    if (e.getTitle().equals(episodeName))
                                        e = new Episode(newEpisodeName, e.getDuration());
                            } else if (option.equals("D")) {
                                System.out.println("Introduceti numele episodului:");
                                String episodeName = scanner.nextLine();
                                System.out.println("Introduceti noua durata a episodului:");
                                String newDuration = scanner.nextLine();
                                for (Episode e : episodeList)
                                    if (e.getTitle().equals(episodeName))
                                        e = new Episode(episodeName, Double.parseDouble(newDuration));
                            }
                        }
                    }
                } else {
                    System.out.println("Nu exista un sezon cu acest nume");
                }
            }
        }

        System.out.println("Modificarile au fost efectuate");
        return;
    }


    @Override
    public void updateActor(Actor actor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Modificare actor " + actor.getName());
        System.out.println("Introduceti numele modificat al actorului(Apasarea tastei ENTER nu modifica valoarea):");
        String newName = scanner.nextLine();
        if(!newName.isEmpty())
            actor.setName(newName);
        System.out.println("Introduceti noua biografie a actorului (Apasarea tastei ENTER nu modifica valoarea):");
        String newBio = scanner.nextLine();
        if(!newBio.isEmpty())
            actor.setBio(newBio);
        String option = "";
        while (!option.equals("F")) {
            System.out.print("Optiuni modifcare performante ADAUGARE-A STERGERE-R FARAMODIFCARI-F [A/R/F]:");
            option =scanner.nextLine();
            if(option.equals("A")){
                System.out.println("Introduceti numele productiei:");
                String title = scanner.nextLine();
                for(Production p:ProductionsManager.getProductions()){
                    if(p.getTitle().equals(title))
                        actor.addPerformances(p);
                }
            } else if (option.equals("R")){
                System.out.println("Introduceti numele productiei:");
                String title = scanner.nextLine();
                List<Pair<Production,String>> modifiedPerformances = new ArrayList<>();
                for(Pair<Production,String> performance:actor.getPerformances()){
                    if(!performance.getLeft().getTitle().equals(title))
                        modifiedPerformances.add(performance);
                }
                actor.setPerformances(modifiedPerformances);
            }
        }
        System.out.println("Modificarile au fost efectuate");
        return;
    }

    @Override
    public void resolveUserRequests() {

    }

    @Override
    public void notify(String message) {
        this.addNotification(message);
    }
}

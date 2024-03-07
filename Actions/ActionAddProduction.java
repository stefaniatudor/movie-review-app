package org.example.Actions;

import org.example.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActionAddProduction implements IMDBAction,ExperienceStrategy {

    private static final String displayableText = "Adaugati o productie";

    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceti tipul productiei (Movie/Series): ");
        String type = scanner.nextLine();

        Production p;
        if (type.equalsIgnoreCase("Movie")) {
            p = getMovie(scanner);
        } else if (type.equalsIgnoreCase("Series")) {
            p = getSeries(scanner);
        } else {
            System.out.println("Tip de productie necunoscut: " + type);
            return;
        }
        ((Staff)loggedUser).addProductionSystem(p);
    }

    private Movie getMovie(Scanner scanner) {
        Movie movie = new Movie();
        addProductionDetails(scanner, movie);
        return movie;
    }

    private Series getSeries(Scanner scanner) {
        Series series = new Series();
        addProductionDetails(scanner, series);
        addSeasons(scanner, series);
        return series;
    }

    private void addProductionDetails(Scanner scanner, Production production) {
        System.out.println("Introduceti titlul: ");
        String title = scanner.nextLine();
        Production existingProduction = ProductionsManager.getProductionByName(title);
        if (existingProduction != null) {
            System.out.println("Productia a fost deja adaugata");
        } else {
            if (title != null) {
                production.setTitle(title);
            }

            addCrewMembers(scanner, production.getDirectors(), "regizorului");
            addCrewMembers(scanner, production.getActors(), "actorului");

            System.out.println("Introduceti actiunea: ");
            String plot = scanner.nextLine();
            if (plot != null) {
                production.setPlot(plot);
            }

            System.out.println("Introduceti anul aparitiei: ");
            Integer releaseYear = Integer.parseInt(scanner.nextLine());
            if (releaseYear != null) {
                production.setReleaseYear(releaseYear);
            }

            addGenres(scanner, production);
        }
    }

    private void addCrewMembers(Scanner scanner, List<String> members, String memberType) {
        System.out.println("Doriti sa adugati " + memberType + "? [Y\\N]: ");
        String option = scanner.nextLine();
        while (option.equalsIgnoreCase("Y")) {
            System.out.println("Introduceti numele " + memberType + ": ");
            String member = scanner.nextLine();
            members.add(member);

            System.out.println("Doriti sa adaugati alt " + memberType + "? [Y\\N]: ");
            option = scanner.nextLine();
        }
    }

    private void addGenres(Scanner scanner, Production production) {
        System.out.println("Doriti sa adugati genuri? [Y\\N]: ");
        String option = scanner.nextLine();
        while (option.equalsIgnoreCase("Y")) {
            System.out.println("Introduceti genul: ");
            Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());
            production.addGenre(genre);

            System.out.println("Doriti sa adaugati alt gen? [Y\\N]: ");
            option = scanner.nextLine();
        }
    }

    private void addSeasons(Scanner scanner, Series series) {
        System.out.println("Doriti sa adugati sezoane? [Y\\N]: ");
        String option = scanner.nextLine();
        while (option.equalsIgnoreCase("Y")) {
            System.out.println("Introduceti titlul sezonului: ");
            String seasonTitle = scanner.nextLine();
            List<Episode> episodes = addEpisodes(scanner);
            series.addSeasons(seasonTitle, episodes);

            System.out.println("Doriti sa adaugati alt sezon? [Y\\N]: ");
            option = scanner.nextLine();
        }
    }

    private List<Episode> addEpisodes(Scanner scanner) {
        List<Episode> episodes = new ArrayList<>();

        System.out.println("Doriti sa adugati episoade? [Y\\N]: ");
        String option = scanner.nextLine();
        while (option.equalsIgnoreCase("Y")) {
            System.out.println("Introduceti titlul episodului: ");
            String epTitle = scanner.nextLine();
            System.out.println("Introduceti durata episodului: ");
            Double epDuration = Double.parseDouble(scanner.nextLine());
            episodes.add(new Episode(epTitle, epDuration));

            System.out.println("Doriti sa adaugati alt episod? [Y\\N]: ");
            option = scanner.nextLine();
        }

        return episodes;
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }

    @Override
    public int calculateExperience() {
        return 15;
    }
}
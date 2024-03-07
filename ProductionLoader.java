package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductionLoader {

    private static Production loadProduction(JsonNode productionNode) {
        String type = Utils.curateString(String.valueOf(productionNode.get("type")));

        if ("Movie".equals(type)) {
            return loadMovie(productionNode);
        } else {
            return loadSeries(productionNode);
        }
    }

    private static Movie loadMovie(JsonNode productionNode) {
        Movie movie = new Movie();
        setCommonProperties(movie, productionNode);

        if (productionNode.has("duration")) {
            movie.setDuration(Utils.curateDuration(String.valueOf(productionNode.get("duration"))));
        }


        return movie;
    }

    private static Series loadSeries(JsonNode productionNode) {
        Series series = new Series();
        setCommonProperties(series, productionNode);

        if (productionNode.has("numSeasons"))
            series.setNumberOfSeasons(Integer.valueOf(String.valueOf(productionNode.get("numSeasons"))));

        if (productionNode.has("seasons")) {
            Iterator seasonIterator = productionNode.get("seasons").fieldNames();
            while (seasonIterator.hasNext()) {
                String seasonName = String.valueOf(seasonIterator.next().toString());
                List<Episode> episodes = new ArrayList<>();
                productionNode.get("seasons").get(seasonName).forEach(es -> {
                    String episodeName = Utils.curateString(String.valueOf(es.get("episodeName")));
                    Double duration = Utils.curateDuration(String.valueOf((es.get("duration"))));
                    episodes.add(new Episode(episodeName, duration));
                });
                series.addSeasons(seasonName, episodes);
            }
        }

        return series;
    }

    private static void setCommonProperties(Production production, JsonNode productionNode) {
        if (productionNode.has("title"))
            production.setTitle(Utils.curateString(String.valueOf(productionNode.get("title"))));

        if (productionNode.has("directors"))
            for (JsonNode director : productionNode.get("directors"))
                production.addDirectors(Utils.curateString(String.valueOf(director)));

        if (productionNode.has("actors"))
            for (JsonNode actor : productionNode.get("actors"))
                production.addActors(Utils.curateString(String.valueOf(actor)));

        if (productionNode.has("ratings"))
            for (JsonNode ratingNode : productionNode.get("ratings"))
                production.addRating(loadRating(ratingNode));

        if (productionNode.has("plot"))
            production.setPlot(Utils.curateString(String.valueOf(productionNode.get("plot"))));

        if (productionNode.has("releaseYear"))
            production.setReleaseYear(Integer.valueOf(String.valueOf(productionNode.get("releaseYear"))));


        if (productionNode.has("genres")) {
            for (JsonNode genre : productionNode.get("genres")) {
                production.addGenre(Genre.valueOf(Utils.curateString(String.valueOf(genre).toUpperCase())));
            }
        }
    }

    private static Rating loadRating(JsonNode ratingNode) {
        String username = null, comment = null;
        Integer rating = null;
        if (ratingNode.has("username"))
            username = Utils.curateString(String.valueOf(ratingNode.get("username")));
        if (ratingNode.has("rating"))
            rating = Integer.parseInt(String.valueOf(ratingNode.get("rating")));
        if (ratingNode.has("comment"))
            comment = Utils.curateString(String.valueOf(ratingNode.get("comment")));

        return new Rating(username, rating, comment);

    }

    public static void loadEntriesFromJSONFile(Utils.FileType fileType) throws IOException {
        String jsonString = Utils.extractTextFromFile(fileType);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode productionNodes = objectMapper.readTree(jsonString);

        for (JsonNode productionNode : productionNodes) {
            Production p = ProductionLoader.loadProduction(productionNode);
            ProductionsManager.addProduction(p);
        }
    }
}

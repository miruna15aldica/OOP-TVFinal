package io.input.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import data.entities.Movie;

import java.io.IOException;
import java.util.List;

public final class MovieDeserializer extends StdDeserializer<Movie> {

    public MovieDeserializer() {
        this(null);
    }

    public MovieDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Movie deserialize(final JsonParser jp, final DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String name = node.get("name").asText();

        String year = node.get("year").asText();

        int duration = (int) (node.get("duration")).numberValue();

        String genresStr = node.get("genres").toPrettyString();
        String actorsStr = node.get("actors").toPrettyString();
        String countriesBannedStr = node.get("countriesBanned").toPrettyString();

        List<String> genres = Utils
                .deserializeList(genresStr, String[].class, null);
        List<String> actors = Utils
                .deserializeList(actorsStr, String[].class, null);
        List<String> countriesBanned = Utils
                .deserializeList(countriesBannedStr, String[].class, null);

        return new Movie(name, year, duration, genres, actors, countriesBanned);
    }
}

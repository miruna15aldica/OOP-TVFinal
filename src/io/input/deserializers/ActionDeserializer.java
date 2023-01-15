package io.input.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import data.actions.*;
import data.entities.Action;
import data.entities.Credentials;
import data.entities.Movie;

import java.io.IOException;
import java.util.Objects;

public final class ActionDeserializer extends StdDeserializer<Action> {

    public ActionDeserializer() {
        this(null);
    }

    public ActionDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Action deserialize(final JsonParser jp, final DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String typeStr = node.get("type").asText()
                .toUpperCase().replace(' ', '_');

        ActionType actionType = ActionType.valueOf(typeStr);

        if (actionType == ActionType.CHANGE_PAGE) {
            String page = node.get("page").asText();

            String movie = Utils.getField(node, "movie");
            if (movie != null) {
                return new ChangePageMovieAction(page, movie);
            }

            return new ChangePageAction(page);
            // Stabilim ce returnma in functie de tipul paginii
        } else if (actionType == ActionType.ON_PAGE) {
            String feature = node.get("feature").asText();
            if (Objects.equals(feature, "register")) {
                String credentialsStr = node.get("credentials").toPrettyString();
                Credentials credentials = Utils
                        .deserialize(credentialsStr, Credentials.class,
                                new CredentialsDeserializer());
                return new UserPageAction(feature, credentials);
            } else if (Objects.equals(feature, "login")) {
                String credentialsStr = node.get("credentials").toPrettyString();
                Credentials credentials = Utils
                        .deserialize(credentialsStr, Credentials.class,
                                new CredentialsDeserializer());
                return new UserPageAction(feature, credentials);
            } else if (Objects.equals(feature, "search")) {
                String page = Utils.getField(node, "page");
                String startsWith = Utils.getField(node, "startsWith");
                return new SearchAction(startsWith, page);
            } else if (Objects.equals(feature, "filter")) {
                String page = Utils.getField(node, "page");
                FilterAction action = new FilterAction(page);
                FilterActionFilters filters = Utils.deserialize(
                        node.get("filters").toString(), FilterActionFilters.class,
                        null
                );
                action.setFilters(filters);
                return action;
            } else if (Objects.equals(feature, "watch") ||
                    Objects.equals(feature, "purchase") || Objects.equals(feature, "like")) {

                return new MovieAction(feature);
            } else if (Objects.equals(feature, "rate")) {
                String rate = Utils.getField(node, "rate");
                return new MovieAction(feature, Integer.parseInt(rate));
            } else if (Objects.equals(feature, "buy premium account")) {
                return new UpgradesAction(feature);
            } else if (Objects.equals(feature, "buy tokens")) {
                String count = Utils.getField(node, "count");
                return new UpgradesAction(feature, Integer.parseInt(count));
            } else if(Objects.equals(feature, "subscribe")) {
                String subscribedGenre = Utils.getField(node, "subscribedGenre");
                return new SubscribeAction(subscribedGenre);
            }
            return null;
        }
        else if(actionType==ActionType.BACK){
            return new BackAction();
        }
        else if(actionType==ActionType.DATABASE){
            String feature = node.get("feature").asText();
            if(Objects.equals(feature, "add")) {
                String addedMovieJson = node.get("addedMovie").toPrettyString();
                Movie addedMovie = Utils
                        .deserialize(addedMovieJson, Movie.class,
                                new MovieDeserializer());
                return new DatabaseAddAction(addedMovie);
            }
            else if(Objects.equals(feature, "delete")) {
                String deletedMovie = node.get("deletedMovie").asText();
                return new DatabaseDeleteAction(deletedMovie);
            }
        }

        return null;
    }
}

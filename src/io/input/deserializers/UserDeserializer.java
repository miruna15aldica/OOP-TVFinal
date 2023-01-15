package io.input.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import data.entities.Credentials;
import data.entities.Notification;
import data.entities.User;

import java.io.IOException;
import java.util.List;

public final class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }

    public UserDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(final JsonParser jp, final DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String credentialsStr = node.get("credentials").toPrettyString();
        Credentials credentials = Utils
                .deserialize(credentialsStr, Credentials.class, new CredentialsDeserializer());

        return new User(credentials);
    }
}

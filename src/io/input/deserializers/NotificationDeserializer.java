package io.input.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import data.entities.AccountType;
import data.entities.Credentials;
import data.entities.Notification;

import java.io.IOException;

public class NotificationDeserializer extends StdDeserializer<Notification> {
    public NotificationDeserializer() {
        this(null);
    }

    public NotificationDeserializer(final Class<?> vc) {
        super(vc);
    }


    @Override
    public Notification deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        String movieName = Utils.getField(node, "movieName");
        String message = Utils.getField(node, "message");
        return new Notification(movieName, message);
    }
}

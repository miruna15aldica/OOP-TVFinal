package io.output.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import data.entities.Notification;

import java.io.IOException;

public class NotificationSerializer extends StdSerializer<Notification> {
    public NotificationSerializer() {
        this(null);
    }

    public NotificationSerializer(final Class<Notification> t) {
        super(t);
    }

    @Override
    public final void serialize(
            final Notification value, final JsonGenerator jgen, final SerializerProvider provider)
        throws IOException {

        jgen.writeStartObject();
        jgen.writeObjectField("movieName", value.getMovieName());
        jgen.writeObjectField("message", value.getMessage());
        jgen.writeEndObject();


    }
}

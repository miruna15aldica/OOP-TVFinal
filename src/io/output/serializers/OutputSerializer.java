package io.output.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import data.entities.Output;

import java.io.IOException;

public final class OutputSerializer extends StdSerializer<Output> {
    public OutputSerializer() {
        this(null);
    }

    public OutputSerializer(final Class<Output> t) {
        super(t);
    }

    @Override
    public void serialize(
            final Output value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeStringField("error", value.getError());
        jgen.writeObjectField("currentMoviesList", value.getCurrentMoviesList());
        jgen.writeObjectField("currentUser", value.getCurrentUser());
        jgen.writeEndObject();
    }
}

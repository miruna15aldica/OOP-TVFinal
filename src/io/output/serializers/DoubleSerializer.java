package io.output.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public final class DoubleSerializer extends StdSerializer<Double> {
    public DoubleSerializer() {
        this(null);
    }

    public DoubleSerializer(final Class<Double> t) {
        super(t);
    }

    @Override
    public void serialize(
            final Double value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException {

        final int ten = 10;
        final int hundred = 100;

        if (null == value) {
            //write the word 'null' if there's no value available
            jgen.writeNull();
        } else {
            int p = (int) Math.floor(value);
            int f = (int) Math.floor((value - p) * hundred);

            final String output = p + "." + (f < ten ? "0" : "") + f;
            jgen.writeNumber(output);
        }
    }
}

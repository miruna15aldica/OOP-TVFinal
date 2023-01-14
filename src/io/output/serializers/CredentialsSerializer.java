package io.output.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import data.entities.AccountType;
import data.entities.Credentials;

import java.io.IOException;

public final class CredentialsSerializer extends StdSerializer<Credentials> {
    public CredentialsSerializer() {
        this(null);
    }

    public CredentialsSerializer(final Class<Credentials> t) {
        super(t);
    }

    @Override
    public void serialize(
            final Credentials value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();

        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("password", value.getPassword());
        jgen.writeStringField("accountType",
                value.getAccountType() == AccountType.STANDARD ? "standard" : "premium");
        jgen.writeStringField("country", value.getCountry());
        jgen.writeStringField("balance", Integer.toString(value.getBalance()));

        jgen.writeEndObject();
    }
}

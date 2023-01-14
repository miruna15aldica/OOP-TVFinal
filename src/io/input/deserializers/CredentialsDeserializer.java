package io.input.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import data.entities.AccountType;
import data.entities.Credentials;

import java.io.IOException;

public final class CredentialsDeserializer extends StdDeserializer<Credentials> {

    public CredentialsDeserializer() {
        this(null);
    }

    public CredentialsDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Credentials deserialize(final JsonParser jp, final DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = Utils.getField(node, "name");
        String password = Utils.getField(node, "password");
        String accTypeStr = Utils.getField(node, "accountType");
        String country = Utils.getField(node, "country");
        String balanceStr = Utils.getField(node, "balance");
        int balance = (balanceStr == null) ? 0 : Integer.parseInt(balanceStr);
        AccountType accountType = (accTypeStr == null)
                ? AccountType.STANDARD
                : AccountType.valueOf(accTypeStr.toUpperCase());
        return new Credentials(name, password, accountType, country, balance);
    }
}

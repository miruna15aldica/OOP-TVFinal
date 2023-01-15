package io.output.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import data.entities.Notification;
import data.entities.User;

import java.io.IOException;

public final class UserSerializer extends StdSerializer<User> {
    public UserSerializer() {
        this(null);
    }

    public UserSerializer(final Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(
            final User value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeObjectField("credentials", value.getCredentials());
        jgen.writeObjectField("tokensCount", value.getTokensCount());
        jgen.writeObjectField("numFreePremiumMovies", value.getNumFreePremiumMovies());
        jgen.writeObjectField("purchasedMovies", value.getPurchasedMovies());
        jgen.writeObjectField("watchedMovies", value.getWatchedMovies());
        jgen.writeObjectField("likedMovies", value.getLikedMovies());
        jgen.writeObjectField("ratedMovies", value.getRatedMovies());
        jgen.writeEndObject();


    }
}

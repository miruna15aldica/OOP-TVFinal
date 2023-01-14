package factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import data.entities.Action;
import data.entities.Movie;
import data.entities.User;
import io.input.InputData;
import io.input.deserializers.ActionDeserializer;
import io.input.deserializers.MovieDeserializer;
import io.input.deserializers.UserDeserializer;

import java.io.File;
import java.io.IOException;

public final class InputDataFactory {
    private InputDataFactory() {
    }

    private static ObjectMapper getDefaultMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());
        module.addDeserializer(Movie.class, new MovieDeserializer());
        module.addDeserializer(Action.class, new ActionDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static InputData fromFile(final String filename) throws IOException {
        return getDefaultMapper().readValue(new File(filename), InputData.class);
    }
}

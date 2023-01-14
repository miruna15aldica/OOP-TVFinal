package io.output.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.util.List;

public final class Utils {
    private Utils() {
    }

    /**
     *
     * @param value
     * @param type
     * @param serializer
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */
    public static <T> String serialize(final T value, final Class<T> type,
                                       final StdSerializer<T> serializer)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        if (serializer != null) {
            SimpleModule module = new SimpleModule();
            module.addSerializer(type, serializer);
            objectMapper.registerModule(module);
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(value);
    }

    /**
     *
     * @param value
     * @param type
     * @param serializer
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */
    public static <T> String serializeList(final List<T> value, final Class<T> type,
                                           final StdSerializer<T> serializer)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        if (serializer != null) {
            SimpleModule module = new SimpleModule();
            module.addSerializer(type, serializer);
            objectMapper.registerModule(module);
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(value);
    }
}

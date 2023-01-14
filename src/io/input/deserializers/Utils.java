package io.input.deserializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Utils {
    private Utils() {
    }

    /**
     *
     * @param input
     * @param type
     * @param deserializer
     * @return
     * @param <T>
     */
    public static <T> T deserialize(final String input, final Class<T> type,
                                    final StdDeserializer<T> deserializer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            if (deserializer != null) {
                SimpleModule module = new SimpleModule();
                module.addDeserializer(type, deserializer);
                objectMapper.registerModule(module);
            }

            return objectMapper.readValue(input, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param input
     * @param type
     * @param deserializer
     * @return
     * @param <T>
     */
    public static <T> List<T> deserializeList(final String input, final Class<T[]> type,
                                              final StdDeserializer<T[]> deserializer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            if (deserializer != null) {
                SimpleModule module = new SimpleModule();
                module.addDeserializer(type, deserializer);
                objectMapper.registerModule(module);
            }

            return Arrays.stream(objectMapper.readValue(input, type)).collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param node
     * @param name
     * @return
     */
    public static String getField(final JsonNode node, final String name) {
        JsonNode n = node.get(name);
        if (n == null) {
            return null;
        }
        return n.asText();
    }
}

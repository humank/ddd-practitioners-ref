package solid.humank.ddd.commons.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

public class DomainModelMapper {

    ObjectMapper mapper;

    public DomainModelMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // override default
        javaTimeModule.addDeserializer(LocalDateTime.class, new MillisOrLocalDateTimeDeserializer());
        mapper = new ObjectMapper();
        mapper.registerModule(javaTimeModule);
        mapper.writerWithDefaultPrettyPrinter();
    }

    public String writeToJsonString(Object object) {
        String result = null;
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return result;
    }

    public <T> T readValue(String json, Class<T> valueType) {
        T result = null;
        try {
            result = mapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T readValue(InputStream inputStream, Class<T> valueType) {
        T result = null;
        try {
            result = mapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
